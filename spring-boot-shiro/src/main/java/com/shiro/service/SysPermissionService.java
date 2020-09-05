package com.shiro.service;

import com.baomidou.mybatisplus.service.IService;
import com.shiro.pojo.SysPermission;
import com.shiro.pojo.UserInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public interface SysPermissionService extends IService<SysPermission> {

    List<SysPermission> selectPermByUser(UserInfo userInfo) throws Exception;
}
