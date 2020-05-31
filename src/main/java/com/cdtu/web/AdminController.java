package com.cdtu.web;

import com.cdtu.entity.Time;
import com.cdtu.entity.User;
import com.cdtu.service.TimeService;
import com.cdtu.util.HrmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private TimeService timeService;

    /**
     * 将设置人信息查询出来，送至考试时间设置页面
     * @param model
     * @param session 在登陆时将登陆人信息设置入session,此处从session中取出信息
     * @return
     */
    @RequestMapping("/test/timeControl")
    public String timeSet(Model model, HttpSession session){
        User  user= (User) session.getAttribute(HrmConstants.USER_SESSION);
        model.addAttribute("user",user);
        return "/timeControl";
    }

    /**
     * 将考试时间范围以及设置考试时间范围的设置人信息存入数据库
     * @param uid
     * @param startTime
     * @param endTime
     * @param createTime 当前设置时的时间
     * @param session
     * @param response
     */
    @RequestMapping(value = "/test/timeControl",method = RequestMethod.POST)
    public void setTime(Long uid, String startTime, String endTime, String createTime, HttpSession session, HttpServletResponse response){
        response.setContentType("text/html; charset=UTF-8");
        if(startTime!="" && endTime!=""){
            String[] starts=startTime.split("-");
            String str1="";
            String[] ends=endTime.split("-");
            String str2="";
            for(int i=0;i<starts.length;i++){
                str1+=starts[i];
                str2+=ends[i];
            }
            Long l1=Long.parseLong(str1);
            Long l2=Long.parseLong(str2);
            if(l1<l2){
                Time time=new Time(uid,startTime,endTime,createTime);
                boolean flag=timeService.addTime(time);
                if(flag==true){
                    try {

                        response.getWriter().println("<script>alert('设置成功')</script>");
                        response.getWriter().close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else  if(flag==false){
                    try {

                        response.getWriter().println("<script>alert('设置失败')</script>");
                        response.getWriter().close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else {
                try {
                    response.getWriter().println("<script>alert('时间设置有误，请重新设置')</script>");
                    response.getWriter().close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
