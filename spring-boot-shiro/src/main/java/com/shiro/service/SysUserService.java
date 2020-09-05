package com.shiro.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shiro.pojo.SysUser;
import com.shiro.pojo.UserInfo;

/**
 * Created by Administrator on 2018/1/12.
 */
public interface SysUserService extends IService<UserInfo> {

    void myTest() throws Exception;

    Page<UserInfo> myTest2() throws Exception;

    UserInfo getUserById(int userId);

    boolean addUser(UserInfo record);

    UserInfo selectByPrimaryKey(int userId);
}
