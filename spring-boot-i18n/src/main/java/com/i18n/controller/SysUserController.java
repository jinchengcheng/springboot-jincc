package com.i18n.controller;

import com.i18n.pojo.SysUser;
import com.i18n.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/showUser")
    @ResponseBody
    public SysUser toIndex(HttpServletRequest request, Model model){
        Integer userId = Integer.parseInt(request.getParameter("id"));
        SysUser user = this.userService.getUserById(userId);
        return user;
    }

}
