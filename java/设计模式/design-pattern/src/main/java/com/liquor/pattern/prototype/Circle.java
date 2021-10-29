package com.liquor.pattern.prototype;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:42
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
