package com.liquor.pattern.facade;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:27
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
