package com.shiro.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shiro.pojo.SysUser;
import com.shiro.pojo.UserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2018/1/10.
 */
public interface SysUserMapper extends BaseMapper<UserInfo> {

    //// TODO: 2018/3/25  此类 除了原本包含的数据库操作方法外，可以自定义，如：
    //@Select("selectAll")
    //List<SysUser> selectAllByPagination(Pagination page, Integer id);
    @Select("select * from user_info where uid=#{id}")
    UserInfo selectById(Integer id);

    UserInfo selectByPrimaryKey(Integer id);
}
