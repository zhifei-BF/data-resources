package com.liquor.springsecuritydemo.model;

import lombok.Data;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 17:14
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

}
