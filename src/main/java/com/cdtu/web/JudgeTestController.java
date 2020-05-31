package com.cdtu.web;

import com.cdtu.entity.JudgeTest;
import com.cdtu.entity.Major;
import com.cdtu.entity.User;
import com.cdtu.service.JudgeTestService;
import com.cdtu.service.MajorService;
import com.cdtu.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/test")
public class JudgeTestController {
    @Autowired
    private JudgeTestService judgeTestService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private UserService userService;

    /**
     * 查询所有判断题，并将所有用户和专业信息一起送至管理员的判断题列表页面
     * @param model
     * @return
     */
    @RequestMapping("/judge/list")
    public String list(Model model,Integer indexPage){
        if(indexPage==null){
            indexPage=1;
        }
        Page startPage= PageHelper.startPage(indexPage,5);
        List<JudgeTest> list=judgeTestService.findAll();
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        model.addAttribute("list",list);
        model.addAttribute("pages",startPage.getPages());
        return "/test/judgeList";
    }

    /**
     * 以post方式将管理员判断题列表上的查询信息送回后端，后端根据查询信息在数据库中查询对应信息，然后送回页面
     * @param find
     * @param value
     * @param model
     * @return
     */
    @RequestMapping(value = "/judge/list",method = RequestMethod.POST)
    public String findList(@RequestParam("find") String find, @RequestParam("value") Long value, Model model){
        if("majorid".equals(find)){
            List<JudgeTest> list=judgeTestService.findByMajorid(value);
            model.addAttribute("list",list);

        }else if("uid".equals(find)){
            List<JudgeTest> list=judgeTestService.findByUid(value);
            model.addAttribute("list",list);
        }
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/judgeList";
    }

    /**
     * 根据请求路径上的判断题id删除数据库中的对应判断题
     * @param jid
     * @param mv
     * @return
     */
    @RequestMapping("/judge/delete")
    public ModelAndView deleteTest(@Param("jid") Long jid, ModelAndView mv){
        boolean flag=judgeTestService.delete(jid);
        if(flag==true){
            mv.addObject("msg","删除成功");
        }else {
            mv.addObject("msg","删除失败");
        }
        mv.setViewName("redirect:/test/judge/list");

        return mv;
    }

    /**
     * 根据请求路径上的判断题id查询具体信息，送至判断题修改界面，并跳转页面
     * @param jid
     * @param model
     * @return
     */
    @RequestMapping("/judge/update")
    public String findJudgeTest(@Param("jid") Long jid,Model model){
        JudgeTest judgeTest=judgeTestService.findByJid(jid);
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("judge",judgeTest);
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/judgeUpdate";
    }

    /**
     * 搜集来自判断题修改界面上的所有信息，并根据判断题id值修改数据库对应信息
     * @param jid
     * @param uid
     * @param majorId
     * @param account
     * @param answer
     * @param score
     * @param comment
     * @param mv
     * @return
     */
    @RequestMapping(value = "/judge/update",method = RequestMethod.POST)
    public ModelAndView update(Long jid,Long uid,Long majorId,String account,int answer,String score,String comment,ModelAndView mv){
        JudgeTest judgeTest=new JudgeTest(jid,account,answer,majorId,Integer.parseInt(score),comment,uid);
        boolean flag=judgeTestService.update(judgeTest);
        if(flag==true){
            mv.addObject("msg","修改成功");
        }else {
            mv.addObject("msg","修改失败");
        }
        mv.setViewName("redirect:/test/judge/list");
        return mv;

    }

    /**
     * 将所有用户和专业信息送至判断题添加界面，并跳转页面
     * @param model
     * @return
     */
    @RequestMapping("/judge/add")
    public String add(Model model){
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "test/judgeAdd";
    }

    /**
     * 搜集判断题添加页面上的所有信息，将信息存入数据库
     * @param account
     * @param answer
     * @param comment
     * @param majorId
     * @param uid
     * @param score
     * @param mv
     * @return
     */
    @RequestMapping(value = "/judge/add",method = RequestMethod.POST)
    public  ModelAndView addJudge(String account,int answer,String comment,Long majorId,Long uid,String score,ModelAndView mv){
        JudgeTest judgeTest=new JudgeTest(account,answer,majorId,Integer.parseInt(score),comment,uid);
        boolean flag=judgeTestService.addJudge(judgeTest);
        if(flag==true){
            mv.addObject("msg","添加成功");
        }else {
            mv.addObject("msg","添加失败");
        }
        mv.setViewName("redirect:/test/judge/list");
        return mv;
    }
}
