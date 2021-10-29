package com.liquor.pattern.prototype;

import java.util.Hashtable;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:42
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class ShapeCache {
    /**
     * 原型模式（Prototype Pattern）是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
     * <p>
     * 这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。
     * 例如，一个对象需要在一个高代价的数据库操作之后被创建。我们可以缓存该对象，
     * 在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。
     */
    private static Hashtable<String, Shape> shapeMap
        = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}
