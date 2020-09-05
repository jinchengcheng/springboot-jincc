package com.i18n.service.impl;

import com.i18n.dao.SysUserMapper;
import com.i18n.pojo.SysUser;
import com.i18n.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
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
    }
}
