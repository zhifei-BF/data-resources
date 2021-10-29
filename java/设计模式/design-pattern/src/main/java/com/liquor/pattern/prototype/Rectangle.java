package com.liquor.pattern.prototype;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:41
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
