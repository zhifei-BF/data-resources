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
public class ShapeMaker {

    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
