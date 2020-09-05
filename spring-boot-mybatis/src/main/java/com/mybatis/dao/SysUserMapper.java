package com.mybatis.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.mybatis.pojo.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author jincc
 * @since 2018-03-25
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    //// TODO: 2018/3/25  此类 除了原本包含的数据库操作方法外，可以自定义，如：
    //@Select("selectAll")
    //List<SysUser> selectAllByPagination(Pagination page, Integer id);
    @Select("select * from sys_user where id=#{id}")
    SysUser selectById(Integer id);

    SysUser selectByPrimaryKey(Integer id);
}
