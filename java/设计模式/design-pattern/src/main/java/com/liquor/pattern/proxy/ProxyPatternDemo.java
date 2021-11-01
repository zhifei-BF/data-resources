package com.liquor.pattern.proxy;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：14:32
 * Description：代理模式
 *
 * @author Liquor
 * @version 1.0.0
 */
public class ProxyPatternDemo {

    /**
     * 在代理模式（Proxy Pattern）中，一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。
     * <p>
     * 在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。
     *
     * @param args
     */

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}
