package com.tuling.service;

import com.tuling.bean.UserBean;
import com.tuling.util.TestData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AuthService {

    private final String demoUserName = "admin";
    private final String demoUserPass = "admin";

    @Resource
    private TestData testData;

    public UserBean userLogin(UserBean user) {
        UserBean queryUser = testData.qeryUser(user);
        if (null != queryUser) {
            queryUser.setUserId(UUID.randomUUID().toString());
        }
        return queryUser;
    }
}