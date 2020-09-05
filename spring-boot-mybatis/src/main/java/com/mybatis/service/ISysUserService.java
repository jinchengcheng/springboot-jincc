package com.mybatis.service;

import com.mybatis.pojo.SysUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author jincc
 * @since 2018-03-24
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser getUserById(int userId);

    boolean addUser(SysUser record);

    SysUser selectByPrimaryKey(int userId);
}
