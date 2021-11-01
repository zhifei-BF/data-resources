package com.liquor.pattern.flyweight;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：14:05
 * Description：圆
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Circle implements Shape {
    //颜色
    private String color;
    private int x;
    private int y;
    //半径
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color
            + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}
