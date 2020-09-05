package com.druid.controller;

import com.druid.pojo.User;
import com.druid.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    ITestService testService;

    @RequestMapping("/findAllUsers")
    @ResponseBody
    public List<User> findAllUsers(HttpServletRequest request, Model model){
        return testService.findAllUsers();
    }

    @RequestMapping("/findUserById")
    @ResponseBody
    public User findUserById(HttpServletRequest request, Model model) {
        User user = testService.findUserById(Integer.valueOf(request.getParameter("id")));
        return user;
    }

    @RequestMapping("/createUser")
    @ResponseBody
    public User createUser(HttpServletRequest request, Model model) {
        return testService.createUser();
    }
}
