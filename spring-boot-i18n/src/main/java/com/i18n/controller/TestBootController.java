package com.i18n.controller;
import com.i18n.pojo.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestBootController {
    @RequestMapping("/getuser")
    public User getUser() {
        User user = new User();
        user.setName("test");
        return user;
    }

    @RequestMapping("/getmsg")
    public String getMsg(HttpServletRequest request, Model model) {
        System.out.println(request.getParameter("msg"));
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        return "hello word from jincc";
    }
}
