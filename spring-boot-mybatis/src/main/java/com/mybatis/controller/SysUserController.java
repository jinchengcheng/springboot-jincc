package com.mybatis.controller;

import com.mybatis.pojo.SysUser;
import com.mybatis.service.ISysUserService;
import com.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserController extends AbstractController{

    /*@Resource
    private IUserService userService;*/
    @Autowired
    private ISysUserService sysUserService;
    @RequestMapping("/showUser")
    @ResponseBody
    public SysUser toIndex(HttpServletRequest request, Model model){
        Integer userId = Integer.parseInt(request.getParameter("id"));
        SysUser user = this.sysUserService.getUserById(userId);
        return user;
    }
    @RequestMapping("/getUser")
    @ResponseBody
    public SysUser getUser(HttpServletRequest request, Model model){
        Integer userId = Integer.parseInt(request.getParameter("id"));
        SysUser user = this.sysUserService.selectByPrimaryKey(userId);
        return user;
    }

}
