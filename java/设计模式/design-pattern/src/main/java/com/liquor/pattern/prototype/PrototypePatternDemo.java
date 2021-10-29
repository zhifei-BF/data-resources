package com.liquor.pattern.prototype;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:44
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class PrototypePatternDemo {
    /**
     * 原型模式包含以下主要角色。
     * 抽象原型类：规定了具体原型对象必须实现的接口。
     * 具体原型类：实现抽象原型类的 clone() 方法，它是可被复制的对象。
     * 访问类：使用具体原型类中的 clone() 方法来复制新的对象。
     */

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
