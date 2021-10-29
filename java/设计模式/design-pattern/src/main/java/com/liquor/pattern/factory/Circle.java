package com.liquor.pattern.factory;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：16:39
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
