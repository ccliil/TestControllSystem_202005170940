package com.cdtu.web;

import com.cdtu.entity.Major;
import com.cdtu.entity.User;
import com.cdtu.entity.VacantTest;
import com.cdtu.service.MajorService;
import com.cdtu.service.UserService;
import com.cdtu.service.VacantService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/test")
public class VacantController {
    @Autowired
    private VacantService vacantService;
    @Autowired
    private UserService userService;
    @Autowired
    private MajorService majorService;

    /**
     * 管理员端填空题列表信息展示
     * @param model
     * @return
     */
    @RequestMapping("/vacant/list")
    public  String listVacant(Model model,Integer indexPage){
        if(indexPage==null){
            indexPage=1;
        }
        Page startPage= PageHelper.startPage(indexPage,5);
        List<VacantTest> list=vacantService.findAll();
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("list",list);
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        model.addAttribute("pages",startPage.getPages());
        return "/test/vacantList";
    }

    /**
     * 管理员端填空题列表页面信息查询和数据返回列表页面
     * @param find
     * @param value
     * @param model
     * @return
     */
    @RequestMapping(value = "/vacant/list",method = RequestMethod.POST)
    public String  vacantList(String find,Long value,Model model){
        if("majorid".equals(find)){
            List<VacantTest> list=vacantService.findByMajorId(value);
            model.addAttribute("list",list);
        }else {
            List<VacantTest> list=vacantService.findByUid(value);
            model.addAttribute("list",list);
        }
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/vacantList";
    }

    /**
     * 管理员端根据路径所带的填空题id删除填空题
     * @param vid
     * @param mv
     * @return
     */
    @RequestMapping("/vacant/delete")
    public ModelAndView delete(Long vid,ModelAndView mv){
        boolean flag=vacantService.delete(vid);
        if(flag==true){
            mv.addObject("msg","删除成功");

        }else {
            mv.addObject("msg","删除失败");
        }
        mv.setViewName("redirect:/test/vacant/list");
        return mv;
    }

    /**
     * 管理员端根据请求路径上的填空题id查询填空题信息并传送至填空题修改页面
     * @param vid
     * @param model
     * @return
     */
    @RequestMapping("/vacant/update")
    public String update(Long vid,Model model){
        VacantTest vacantTest=vacantService.findByVid(vid);
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("vacant",vacantTest);
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/vacantUpdate";
    }

    /**
     * 搜集填空题页面信息，根据id值修改对应数据库信息
     * @param vid
     * @param account
     * @param comment
     * @param answer
     * @param score
     * @param uid
     * @param majorId
     * @param mv
     * @return
     */
    @RequestMapping(value = "/vacant/update",method = RequestMethod.POST)
    public ModelAndView updateVacant(Long vid,String account,String comment,String answer,String score,Long uid,Long majorId,ModelAndView mv){
        VacantTest vacantTest=new VacantTest(vid,account,answer,majorId,Integer.parseInt(score),comment,uid);
        boolean flag=vacantService.updateVacant(vacantTest);
        if(flag==true){
            mv.addObject("msg","修改成功");
        }else {
            mv.addObject("msg","修改失败");
        }
        mv.setViewName("redirect:/test/vacant/list");
        return mv;
    }

    /**
     * 填空题添加页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/vacant/add")
    public String  addEntity(Model model){
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/vacantAdd";
    }

    /**
     * 搜集填空题添加页面上的信息并存入数据库
     * @param account
     * @param answer
     * @param comment
     * @param score
     * @param majorId
     * @param uid
     * @param mv
     * @return
     */
    @RequestMapping(value = "/vacant/add",method = RequestMethod.POST)
    public ModelAndView addVacant(String account,String answer,String comment,String score,Long majorId,Long uid,ModelAndView mv){
        VacantTest vacantTest=new VacantTest(account,answer,majorId,Integer.parseInt(score),comment,uid);
        boolean flag=vacantService.add(vacantTest);
        if(flag==true){
            mv.addObject("msg","添加成功");
        }else {
            mv.addObject("msg","添加失败");
        }
        mv.setViewName("redirect:/test/vacant/list");
        return mv;
    }
}
