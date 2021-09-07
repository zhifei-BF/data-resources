package com.liquor.writespring.liquor;

import com.liquor.writespring.liquor.service.UserService;
import com.liquor.writespring.spring.LiquorApplicationContext;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/7 18:23
 */
public class Test {
    public static void main(String[] args) {
        LiquorApplicationContext applicationContext = new LiquorApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
