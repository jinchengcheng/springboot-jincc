package com.mybatis.service.impl;

import com.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service("userService")
public class UserServiceImpl implements IUserService {
    /*@Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser getUserById(int userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(SysUser record) {
        boolean result = false;
        try {
            sysUserMapper.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }*/
}
