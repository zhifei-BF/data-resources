package com.liquor.springdemo;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/9 11:12
 */
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        System.out.println("初始化时要执行的方法");
    }

    public void destroy(){
        System.out.println("销毁时要执行的方法");
    }
}
