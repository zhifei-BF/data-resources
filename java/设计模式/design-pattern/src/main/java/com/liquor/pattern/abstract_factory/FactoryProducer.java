package com.liquor.pattern.abstract_factory;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：16:59
 * Description：工厂生产者
 *
 * @author Liquor
 * @version 1.0.0
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}
