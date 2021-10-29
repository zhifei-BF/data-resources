package com.liquor.pattern.builder;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:11
 * Description：建造者
 *
 * @author Liquor
 * @version 1.0.0
 */
public class MealBuilder {
    /**
     * 建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
     * <p>
     * 一个 Builder 类会一步一步构造最终的对象。该 Builder 类是独立于其他对象的。
     */

    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
