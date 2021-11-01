package com.liquor.pattern.decorator;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:09
 * Description：长方形
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
