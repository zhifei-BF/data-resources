package com.liquor.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 15:30
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/demo")
    public String demo(){
        return "spring security demo";
    }

    @GetMapping("/index")
    public String index(){
        return "admin index";
    }
}
