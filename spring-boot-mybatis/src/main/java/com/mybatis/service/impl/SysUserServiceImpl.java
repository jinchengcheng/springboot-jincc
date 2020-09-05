package com.mybatis.service.impl;

import com.mybatis.dao.SysUserMapper;
import com.mybatis.pojo.SysUser;
import com.mybatis.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author jincc
 * @since 2018-03-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public SysUser getUserById(int userId) {
        return baseMapper.selectById(userId);
        //return baseMapper.selectById(userId);
        //return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser selectByPrimaryKey(int userId) {
        return baseMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(SysUser record) {
        boolean result = false;
        try {
            SysUser user = new SysUser();
            user.setLoginName("test");
            user.setEmail("4429619@qq.com");
            user.setPassword("test");
            user.setName("test");
            int ct = baseMapper.insert(user);
            //sysUserMapper.insertSelective(record);
            if(ct > 0)
                result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
