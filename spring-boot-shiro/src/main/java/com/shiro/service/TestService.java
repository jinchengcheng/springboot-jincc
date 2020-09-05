package com.shiro.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiro.dao.SysRoleMapper;
import com.shiro.pojo.SysRole;

import java.util.List;

public interface TestService {


    List<SysRole>  sysRoleForPageService(Page<SysRole> page, Integer start, String name) throws Exception;

}
