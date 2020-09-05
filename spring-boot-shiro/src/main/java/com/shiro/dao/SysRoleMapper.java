package com.shiro.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shiro.pojo.SysRole;
import com.shiro.pojo.UserInfo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectRoleByUser(UserInfo userInfo);
}
