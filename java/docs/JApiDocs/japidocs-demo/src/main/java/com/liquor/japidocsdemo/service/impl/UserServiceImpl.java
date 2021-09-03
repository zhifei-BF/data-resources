package com.liquor.japidocsdemo.service.impl;

import com.liquor.japidocsdemo.model.User;
import com.liquor.japidocsdemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/3 17:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("liquor");
        user.setPassword("123456");
        user.setAge(18);
        user.setSex("男");
        return user;
    }
}
