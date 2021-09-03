package com.liquor.japidocsdemo.controller;

import com.liquor.japidocsdemo.model.User;
import com.liquor.japidocsdemo.service.UserService;
import io.github.yedaxia.apidocs.ApiDoc;
import io.github.yedaxia.apidocs.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/3 16:44
 */

/**
 * 用户模块
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户名
     *
     * @return
     */
    @GetMapping("/username")
    @ApiDoc(url = "/user/username")
    public String getUsername() {
        return "JApiDocs";
    }


    /**
     * 获取某个用户
     * @param user
     * @return
     */
    @ApiDoc(url = "/user/findById")
    @GetMapping("/findById")
    public User findById(@RequestBody User user){
        return userService.findById();
    }

}
