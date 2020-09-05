package com.i18n.service;


import com.i18n.pojo.SysUser;

public interface IUserService {
    public SysUser getUserById(int userId);

    boolean addUser(SysUser record);
}
