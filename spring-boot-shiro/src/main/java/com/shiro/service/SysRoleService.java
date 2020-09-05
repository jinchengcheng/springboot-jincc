package com.shiro.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.shiro.pojo.SysRole;
import com.shiro.pojo.UserInfo;
import com.shiro.utils.model.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> selectRoleByUser(UserInfo userInfo) throws Exception;

    /**
     * 查询角色
     * @param sysRole
     * @return
     * @throws Exception
     */
    Page<SysRole> selectSysRoles(Page<SysRole> page, Integer length, String name) throws Exception;

    ResponseModel<Object> createOrUpdateSysRoles(SysRole sysRole) throws Exception;

    ResponseModel<Object> deleteSysRoles(List<String> ids) throws Exception;
}
