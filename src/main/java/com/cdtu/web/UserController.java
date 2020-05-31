package com.cdtu.web;

import com.cdtu.entity.Major;
import com.cdtu.entity.User;
import com.cdtu.service.MajorService;
import com.cdtu.service.UserService;
import com.cdtu.util.HrmConstants;
import com.cdtu.util.MD5Util;
import com.cdtu.util.SmsUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private MajorService majorService;
    private String code;

    /**
     * 管理员端所有用户情况列表展示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer indexPage) {
        if (indexPage == null) {
            indexPage = 1;
        }
        Page startPage = PageHelper.startPage(indexPage, 5);
        List<User> list = userService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("pages", startPage.getPages());
        return "user/list";// WEB-INF/jsp/"list".jsp
    }

    /**
     * 搜集用户列表查询信息，在数据库中查找并将查找数据返回列表页面
     *
     * @param operation
     * @param find
     * @param value
     * @param mv
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView list2(@RequestParam("operation") String operation, @RequestParam("find") String find, @RequestParam("value") String value, ModelAndView mv) {
        if ("查找".equals(operation)) {
            if ("".equals(value)) {
                List<User> list = userService.findAll();
                mv.addObject("list", list);
            } else {
                if ("username".equals(find)) {
                    List<User> list = userService.findByUsername(value);
                    mv.addObject("list", list);
                } else {
                    List<User> list = (List<User>) userService.findByName(value);
                    mv.addObject("list", list);

                }
            }
            mv.setViewName("/user/list");
        } else if ("添加用户".equals(operation)) {
            mv.setViewName("redirect:/user/addUser");
        }
        return mv;
    }

    /**
     * 登录页面的跳转
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login2() {
        return "/login";
    }

    /**
     * 判断用户是否可登录，并根据登录身份进行页面跳转并将登录信息存入session
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, HttpSession session, Model model) {
        User user = null;
        String viewName = "/login";
        try {
            password = MD5Util.MD5(password).substring(0, 30);
            System.out.println(password);
            user = userService.login(username, password);
            session.setAttribute(HrmConstants.USER_SESSION, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            viewName = "redirect:/";
        } else {
            model.addAttribute("message", "登录名或密码错误！请重新输入");
        }
        return viewName;
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute(HrmConstants.USER_SESSION);
        session.invalidate();
        return "redirect:/user/login";
    }

    /**
     * 登录后的登陆人基本信息展示
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/login/userInfo")
    public String getLogin(HttpSession session, Model model) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("user", user);
        Major major = majorService.findById(user.getMajorid());
        model.addAttribute("major", major);
        return "/user/userInfo";
    }

    /*
     * 修改密码的短信验证
     *
     * @param request
     * @return
     */
    @RequestMapping("/sendSms")
    public String sendMsg(HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        String tel = request.getParameter("tel");
        String username = request.getParameter("username");
        if ("".equals(tel) || "".equals(username) || tel == null || username == null) {
            modelMap.addFlashAttribute("message", "请填写完整");
            return "redirect:resetPassword";
        } else {
            User user = userService.findByNamePerson(username, tel);
            if (user != null) {
                code = SmsUtils.createCode();
                try {
                    SmsUtils.sendSms(tel, code);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                modelMap.addFlashAttribute("username", username);
                modelMap.addFlashAttribute("tel", tel);
                return "redirect:resetPassword";
            } else {
                modelMap.addFlashAttribute("message", "不存在匹配用户");
                return "redirect:resetPassword";
            }
        }

    }

    /**
     * 忘记密码的页面跳转
     *
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String reSet() {
        System.out.println("页面跳转");
        return "/resetPas";
    }

    /**
     * 将忘记密码页面的信息搜集起来，判断短信验证码以进行数据库个人信息的修改
     *
     * @param request
     * @param response
     * @param mv
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView reSetPas(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");
        String tel = request.getParameter("tel");
        String passwordMD = MD5Util.MD5(request.getParameter("password")).substring(0, 30);
        String Code = request.getParameter("code");
        if ("".equals(userName) && userName != null && "".equals(tel) && tel != null) {
            User user = (User) userService.findByNamePerson(userName, tel);
            if (user != null) {
                if (code.equals(Code)) {
                    boolean flags = userService.updateUser(tel, passwordMD, request.getParameter("password"));
                    try {
                        if (flags == true) {
                            response.getWriter().println("<script language='javaScript'> alert('密码修改成功');</script>");
                            mv.setViewName("/login");
                        } else {
                            mv.addObject("message", "密码修改失败，请重试");
                            response.getWriter().println("<script language='javaScript'> alert('密码修改失败，请重试');</script>");
                            mv.setViewName("/resetPas");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            try {
                mv.addObject("message", "用户名不存在，请重试");
                response.getWriter().println("<script language='javaScript'> alert('用户名不存在，请重试');</script>");
                mv.setViewName("/resetPas");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return mv;
    }

    /**
     * 管理员端的用户信息修改页面跳转
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String getUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        List<Major> list = majorService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "/user/update";
    }

    /**
     * 管理员端的用户信息修改具体操作
     *
     * @param id
     * @param username
     * @param password
     * @param name
     * @param tel
     * @param majorid
     * @param identify
     * @param mv
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("id") Long id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("tel") String tel, @RequestParam("majorid") String majorid, @RequestParam("identify") String identify, ModelAndView mv) {
        Long mid = Long.parseLong(majorid);
        User user = new User(id, username, MD5Util.MD5(password).substring(0, 30), tel, name, identify, mid, password);
        boolean flag = userService.userUpdate(user);
        if (flag == true) {
            mv.addObject("msg", "修改成功");
        } else {
            mv.addObject("msg", "修改失败");
        }
        mv.setViewName("redirect:/user/list");
        return mv;
    }

    /**
     * 管理员端的用户删除
     *
     * @param id
     * @param mv
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView dropUser(@RequestParam("id") Long id, ModelAndView mv) {
        boolean flag = userService.dropUser(id);
        if (flag == true) {
            mv.addObject("msg", "删除成功");
        } else {
            mv.addObject("msg", "删除失败");
        }
        mv.setViewName("redirect:/user/list");
        return mv;
    }

    /**
     * 管理员端的添加用户页面跳转
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        List<Major> list = majorService.findAll();
        model.addAttribute("list", list);
        return "/user/userAdd";
    }

    /**
     * 管理员端的添加用户具体操作
     *
     * @param username
     * @param password
     * @param name
     * @param identify
     * @param tel
     * @param majorid
     * @param mv
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView userAdd(@Param("username") String username, @Param("password") String password, @Param("name") String name, @Param("identify") String identify, @Param("tel") String tel, @Param("majorid") String majorid, ModelAndView mv) {
        User user = new User(username, MD5Util.MD5(password).substring(0, 30), tel, name, identify, Long.parseLong(majorid), password);
        int flag = userService.insertUser(user);
        if (flag == 1) {
            mv.addObject("msg", "插入成功");
            mv.setViewName("redirect:/user/list");
        } else {
            mv.addObject("msg", "插入失败");
            mv.setViewName("/user/userAdd");
        }
        return mv;
    }

    /**
     * 欢迎页面的跳转（登录人信息以及登陆时间的显示）
     *
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(HttpSession session,Model model) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("user",user);
        return "/user/welcome";
    }

    @RequestMapping(value = "/updatePersonInfo", method = RequestMethod.GET)
    public String updatePersonInfo(Model model, HttpSession session) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("user", user);
        return "/updatePerson";
    }

    @RequestMapping(value = "/updatePersonInfo", method = RequestMethod.POST)
    public String updateSucceed(HttpServletRequest request, Model model, HttpSession session) {
        Long id = Long.parseLong(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String passMd = MD5Util.MD5(password).substring(0, 30);
        User user = new User(id, username, passMd, tel, name, password);

        boolean flag = userService.updatePersonInfo(user);
        if (flag) {
            model.addAttribute("msg", "修改成功");
            System.out.println(id);
            User user2 = userService.findInfoById(id);
            System.out.println(user2);
            model.addAttribute("user", user2);
            session.setAttribute(HrmConstants.USER_SESSION, user2);
            System.out.println((User) session.getAttribute("user"));
        } else {
            model.addAttribute("msg", "修改失败");
        }
        return "/updatePerson";
    }
}
