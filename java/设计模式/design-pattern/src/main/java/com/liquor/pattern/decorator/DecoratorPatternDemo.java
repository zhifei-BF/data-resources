package com.liquor.pattern.decorator;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:14
 * Description：装饰器模式
 *
 * @author Liquor
 * @version 1.0.0
 */
public class DecoratorPatternDemo {
    /**
     * 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
     * <p>
     * 这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
     * <p>
     * 我们将创建一个 Shape 接口和实现了 Shape 接口的实体类。然后我们创建一个实现了 Shape 接口的抽象装饰类 ShapeDecorator，并把 Shape 对象作为它的实例变量。
     * <p>
     * RedShapeDecorator 是实现了 ShapeDecorator 的实体类。
     * <p>
     * DecoratorPatternDemo 类使用 RedShapeDecorator 来装饰 Shape 对象。
     *
     * @param args
     */
    public static void main(String[] args) {

        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
