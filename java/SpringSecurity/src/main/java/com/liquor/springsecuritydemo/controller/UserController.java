package com.liquor.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 18:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login() {
        return "ok";
    }
}
