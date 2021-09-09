package com.liquor.springdemo;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/9 17:21
 */
@Component
public class LiquorFactoryBean implements FactoryBean {
    //只会经过初始化后
    @Override
    public Object getObject() throws Exception {
        UserService userService = new UserService();
        return userService;
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }
}
