package com.liquor.pattern.facade;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:27
 * Description：外观模式
 *
 * @author Liquor
 * @version 1.0.0
 */
public class FacadePatternDemo {

    /**
     * 外观模式（Facade Pattern）隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。这种类型的设计模式属于结构型模式，
     * 它向现有的系统添加一个接口，来隐藏系统的复杂性。
     * <p>
     * 我们将创建一个 Shape 接口和实现了 Shape 接口的实体类。下一步是定义一个外观类 ShapeMaker。
     * <p>
     * ShapeMaker 类使用实体类来代表用户对这些类的调用。FacadePatternDemo 类使用 ShapeMaker 类来显示结果。
     *
     * @param args
     */
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
