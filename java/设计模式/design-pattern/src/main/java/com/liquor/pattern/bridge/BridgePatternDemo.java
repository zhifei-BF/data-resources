package com.liquor.pattern.bridge;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：9:59
 * Description：桥接模式
 *
 * @author Liquor
 * @version 1.0.0
 */
public class BridgePatternDemo {
    /**
     * 我们有一个作为桥接实现的 DrawAPI 接口和实现了 DrawAPI 接口的实体类 RedCircle、GreenCircle。
     * Shape 是一个抽象类，将使用 DrawAPI 的对象。BridgePatternDemo 类使用 Shape 类来画出不同颜色的圆。
     *
     * @param args
     */

    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }

}
