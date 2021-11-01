package com.liquor.pattern.command;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:11
 * Description：请求类
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + ",Quantity:" + quantity +" ]bought ");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + ",Quantity:" + quantity +" ]sold ");
    }
}
