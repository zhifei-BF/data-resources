package com.liquor.pattern.decorator;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:11
 * Description：抽象装饰类
 *
 * @author Liquor
 * @version 1.0.0
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}
