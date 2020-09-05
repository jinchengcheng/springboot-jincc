package com.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiro.dao.SysPermissionMapper;
import com.shiro.pojo.SysPermission;
import com.shiro.pojo.UserInfo;
import com.shiro.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermission> selectPermByUser(UserInfo userInfo) throws Exception {
        return baseMapper.selectPermByUser(userInfo);
    }
}
