package com.cdtu.web;

import com.cdtu.entity.ChioceTest;
import com.cdtu.entity.Major;
import com.cdtu.entity.User;
import com.cdtu.service.ChioceTestService;
import com.cdtu.service.MajorService;
import com.cdtu.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class ChioceTestController {
    @Autowired
    private ChioceTestService chioceTestService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private UserService userService;

    /**
     * 将所有的选择题信息以及所有用户id和name,和专业mid、name送至管理员的所有选择题页面
     * @param model
     * @return
     */
    @RequestMapping("/chioce/list")
    public String testList(Model model,Integer indexPage){
        if(indexPage==null){
            indexPage=1;
        }
        Page startPage= PageHelper.startPage(indexPage,5);
        List<ChioceTest> list=chioceTestService.findAll();
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        model.addAttribute("list",list);
        model.addAttribute("pages",startPage.getPages());
        return "/test/chioceList";
    }

    /**
     * 获取选择题页面返回回来的查找信息，并根据查找信息到数据库中查找送回选择题的列表界面
     * @param find
     * @param value
     * @param model
     * @return
     */
    @RequestMapping(value = "/chioce/list",method = RequestMethod.POST)
    public String findList(@RequestParam("find") String find,@RequestParam("value") Long value,Model model){
        if("majorid".equals(find)){
            List<ChioceTest> list=chioceTestService.findByMajorid(value);
           model.addAttribute("list",list);

        }else if("uid".equals(find)){
            List<ChioceTest> list=chioceTestService.findByUid(value);
           model.addAttribute("list",list);
        }
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/chioceList";
    }

    /**
     * 根据请求路径上传送的选择题id删除对应选择题
     * @param cid
     * @param mv
     * @return
     */
    @RequestMapping("/chioce/delete")
    public ModelAndView deleteChioce(@RequestParam("cid") Long cid, ModelAndView mv){
        boolean flag=chioceTestService.delete(cid);
        if(flag==true){
            mv.addObject("msg","删除成功");

        }else {
            mv.addObject("msg","删除失败");
        }
        mv.setViewName("redirect:/test/chioce/list");
        return mv;
    }

    /**
     * 根据请求路径上的选择题id，查找具体的值，然后与所有用户信息以及所有专业信息送至修改界面
     * @param cid
     * @param model
     * @return
     */
    @RequestMapping("/chioce/update")
    public String findChioceTest(@RequestParam("cid") Long cid,Model model){
        ChioceTest chioceTest=chioceTestService.findByCid(cid);
       List<User> listUser=userService.findAll();
       List<Major> listMajor=majorService.findAll();
        model.addAttribute("chioce",chioceTest);
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/chioceUpdate";
    }

    /**
     * 回收修改选择题表单上的对应值，修改选择题id对应的其他信息
     * @param cid
     * @param account
     * @param aitem
     * @param bitem
     * @param citem
     * @param ditem
     * @param answer
     * @param score
     * @param uid
     * @param majorid
     * @param mv
     * @return
     */
    @RequestMapping(value = "/chioce/update",method = RequestMethod.POST)
    public ModelAndView updateChioce(@RequestParam("cid") Long cid,@RequestParam("account") String account,@RequestParam("aitem") String aitem,@RequestParam("bitem") String bitem,@RequestParam("citem") String citem,@RequestParam("ditem") String ditem,@RequestParam("answer") String answer,@RequestParam("score") String score,@RequestParam("uid") Long uid,@RequestParam("majorid") Long majorid,ModelAndView mv){
       ChioceTest chioceTest=new ChioceTest(cid,account,aitem,bitem,citem,ditem,answer,Integer.parseInt(score),majorid,uid);
       boolean flag=chioceTestService.updateChioce(chioceTest);
       if(flag==true){
           mv.addObject("msg","修改成功");

       }else {
           mv.addObject("msg","修改失败");
       }
        mv.setViewName("redirect:/test/chioce/list");
        return  mv;
    }

    /**
     * 将所有的用户信息和专业信息送至选择题添加界面，并跳转界面
     * @param model
     * @return
     */
    @RequestMapping("/chioce/add")
    public  String getEntity(Model model){
        List<User> listUser=userService.findAll();
        List<Major> listMajor=majorService.findAll();
        model.addAttribute("listUser",listUser);
        model.addAttribute("listMajor",listMajor);
        return "/test/chioceAdd";
    }

    /**
     * 将添加的选择题信息插入数据库
     * @param account
     * @param comment
     * @param aitem
     * @param bitem
     * @param citem
     * @param ditem
     * @param answer
     * @param score
     * @param uid
     * @param majorid
     * @param mv
     * @return
     */
    @RequestMapping(value = "/chioce/add",method = RequestMethod.POST)
    public ModelAndView addChioce(String account,String comment,String aitem,String bitem,String citem,String ditem,String answer,String score,Long uid,Long majorid,ModelAndView mv){
        ChioceTest chioceTest=new ChioceTest(account,aitem,bitem,citem,ditem,answer,Integer.parseInt(score),majorid,false,comment,uid);
        boolean flag=chioceTestService.addChioce(chioceTest);
        if(flag==true){
            mv.addObject("msg","题目添加成功");
        }else {
            mv.addObject("msg","题目添加失败");
        }
        mv.setViewName("redirect:/test/chioce/list");
        return mv;
    }
}
