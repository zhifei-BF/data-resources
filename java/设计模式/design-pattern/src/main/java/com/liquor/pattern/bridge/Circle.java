package com.liquor.pattern.bridge;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：9:58
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Circle extends Shape {

    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
