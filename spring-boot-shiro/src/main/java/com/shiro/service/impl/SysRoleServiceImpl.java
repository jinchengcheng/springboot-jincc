package com.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiro.config.BlankUtil;
import com.shiro.dao.SysRoleMapper;
import com.shiro.pojo.SysRole;
import com.shiro.pojo.UserInfo;
import com.shiro.service.SysRoleService;
import com.shiro.utils.model.ResponseModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> selectRoleByUser(UserInfo userInfo) throws Exception{
        return baseMapper.selectRoleByUser(userInfo);
    }

    @Override
    public Page<SysRole> selectSysRoles(Page<SysRole> page, Integer length, String name) throws Exception {
        EntityWrapper<SysRole> params = new EntityWrapper<>();
        if(!BlankUtil.isBlank(name)) {
            params.like("role",name);
        }
        return page.setRecords(baseMapper.selectPage(page, params));
    }

    @Override
    public ResponseModel<Object> createOrUpdateSysRoles(SysRole sysRole) {
        ResponseModel<Object> result;
        try {
            if (!BlankUtil.isBlank(sysRole.getId()) && sysRole.getId()!=0) {
                baseMapper.updateById(sysRole);
                result = new ResponseModel<Object>("success","修改成功");
            } else {
                baseMapper.insert(sysRole);
                result = new ResponseModel<Object>("success","新增成功");
            }

        } catch(RuntimeException e) {
            e.printStackTrace();
            result = new ResponseModel<>("error", "操作失败");
        }

        return result;
    }

    @Override
    public ResponseModel<Object> deleteSysRoles(List<String> ids) throws Exception {
        ResponseModel<Object> result;
        try {
            baseMapper.deleteBatchIds(ids);
            result = new ResponseModel<Object>("success", "删除成功");
        }catch (RuntimeException e) {
            e.printStackTrace();
            result = new ResponseModel<Object>("error", "删除失败");
        }
        return result;
    }
}
