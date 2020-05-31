package com.cdtu.web;

import com.cdtu.entity.User;
import com.cdtu.util.HrmConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
        String viewName = "/student";
        String identify = user.getIdentify();
        if ("管理员".equals(identify)) {
            viewName = "/admin";
        } else if ("老师".equals(identify)) {
            viewName = "/teacher";
        } else {
            viewName = "/student";
        }
        model.addAttribute("user", user);
        return viewName;
    }
}
