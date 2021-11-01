package com.liquor.pattern.decorator;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:10
 * Description：圆形
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
