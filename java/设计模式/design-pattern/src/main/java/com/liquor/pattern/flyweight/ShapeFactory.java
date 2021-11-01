package com.liquor.pattern.flyweight;

import java.util.HashMap;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：14:05
 * Description：形状工厂
 *
 * @author Liquor
 * @version 1.0.0
 */
public class ShapeFactory {

    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
