package com.shiro.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiro.dao.SysUserMapper;
import com.shiro.pojo.SysUser;
import com.shiro.pojo.UserInfo;
import com.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Administrator on 2018/1/12.
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,UserInfo> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserInfo getUserById(int userId) {
        return baseMapper.selectById(userId);
        //return baseMapper.selectById(userId);
        //return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserInfo selectByPrimaryKey(int userId) {
        return baseMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(UserInfo record) {
        boolean result = false;
        try {
            UserInfo user = new UserInfo();
            /*user.setLoginName("test");
            user.setEmail("4429619@qq.com");
            user.setPassword("test");
            user.setName("test");*/
            int ct = baseMapper.insert(user);
            //sysUserMapper.insertSelective(record);
            if(ct > 0)
                result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void myTest() throws Exception {
//        DbContextHolder.setDbType(DBTypeEnum.shiro);
        UserInfo user = new UserInfo();
//        user.setId(UUID.randomUUID().toString());
        user.setUsername("alice");
        user.setPassword("123456");
        baseMapper.insert(user);
//        user.insert();
//        Aatest aatest = new Aatest();
//        aatest.setProid("123");
//        aatest.setContent("ksjdjf");
//        aatest.insert();
//        aatest.insert();
//        int i = 1/0;
//        System.err.println("查询插入结果：" + user.selectById().getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Page<UserInfo> myTest2() throws Exception {
//        DbContextHolder.setDbType(DBTypeEnum.shiro);
        UserInfo user = new UserInfo();
//        System.err.println("删除所有：" + user.delete(null));
        user.setUsername("alice");
        user.setPassword("123");
//        user.setId(UUID.randomUUID().toString());
        user.insert();
        System.err.println("查询插入结果：" + user.selectById().getUsername());
        user.setUsername("mybatis-plus-ar");
        System.err.println("更新：" + user.updateById());
        return user.selectPage(new Page<>(0, 12), null);
    }
}
