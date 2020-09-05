package com.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.shiro.dao.UserInfoMapper;
import com.shiro.pojo.UserInfo;
import com.shiro.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {



}
