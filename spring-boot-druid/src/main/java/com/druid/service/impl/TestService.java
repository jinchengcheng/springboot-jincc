package com.druid.service.impl;

import com.druid.dao.UserRepository;
import com.druid.pojo.User;
import com.druid.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("testService")
public class TestService implements ITestService{

    private Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @Transactional
    @Override
    public User findUserById(Integer id) {
        User user = userRepository.findUserById(id);
        return user;
    }
    @Transactional
    @Override
    public User createUser() {
        User user = new User(0, "tom", "tom@gmail.com");
        User savedUser = userRepository.create(user);
        logger.debug("{}",savedUser);
        //User newUser = userRepository.findUserById(savedUser.getId());
        return savedUser;
    }
}
