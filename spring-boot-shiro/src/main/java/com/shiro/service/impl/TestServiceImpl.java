package com.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiro.config.BlankUtil;
import com.shiro.dao.SysRoleMapper;
import com.shiro.pojo.SysRole;
import com.shiro.service.TestService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements TestService {
    @Override
    public List<SysRole> sysRoleForPageService(Page<SysRole> page, Integer start, String name) throws Exception {
        EntityWrapper<SysRole> params = new EntityWrapper<>();
        if(!BlankUtil.isBlank(name)) {
            params.like("role",name);
        }
        //page.setRecords(baseMapper.selectPage(page, params)).getRecords();
        return baseMapper.selectPage(page, params);
    }
}
