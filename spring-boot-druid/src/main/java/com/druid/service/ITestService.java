package com.druid.service;

import com.druid.pojo.User;

import java.util.List;

public interface ITestService {

    List<User> findAllUsers();
    User findUserById(Integer id);
    User createUser();
}
