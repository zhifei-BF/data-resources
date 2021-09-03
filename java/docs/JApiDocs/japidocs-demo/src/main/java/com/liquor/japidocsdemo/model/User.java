package com.liquor.japidocsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/3 17:25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户年龄
     */
    private Integer age;
}
