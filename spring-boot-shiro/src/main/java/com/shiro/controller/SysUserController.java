package com.shiro.controller;

import com.shiro.pojo.SysUser;
import com.shiro.pojo.UserInfo;
import com.shiro.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class SysUserController extends AbstractController {

    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    /*@Resource
    private IUserService userService;*/
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/showUser")
    @ResponseBody
    public UserInfo toIndex(HttpServletRequest request, Model model){
        Integer userId = Integer.parseInt(request.getParameter("id"));
        UserInfo user = this.sysUserService.getUserById(userId);

        logger.info("测试{},aa{},bb{}",new Object[]{1,2,3});
        return user;
    }
    @RequestMapping("/getUser")
    @ResponseBody
    @RequiresPermissions("userInfo:del")
    public UserInfo getUser(HttpServletRequest request, Model model){
        Integer userId = Integer.parseInt(request.getParameter("id"));
        UserInfo user = this.sysUserService.selectByPrimaryKey(userId);
        return user;
    }

}
