package com.liquor.pattern.decorator;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:13
 * Description：实体装饰类
 *
 * @author Liquor
 * @version 1.0.0
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }
}
