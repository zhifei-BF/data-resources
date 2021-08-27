package com.liquor.springsecuritydemo.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 17:17
 */
@Data
public class Permission {

    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private String description;

    private Date created;

    private Date updated;

    private String enname;
}
