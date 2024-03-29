package com.liquor.pattern.builder;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:11
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class BuilderPatternDemo {
    /**
     * 我们假设一个快餐店的商业案例，其中，一个典型的套餐可以是一个汉堡（Burger）和一杯冷饮（Cold drink）。
     * 汉堡（Burger）可以是素食汉堡（Veg Burger）或鸡肉汉堡（Chicken Burger），它们是包在纸盒中。
     * 冷饮（Cold drink）可以是可口可乐（coke）或百事可乐（pepsi），它们是装在瓶子中。
     * <p>
     * 我们将创建一个表示食物条目（比如汉堡和冷饮）的 Item 接口和实现 Item 接口的实体类，
     * 以及一个表示食物包装的 Packing 接口和实现 Packing 接口的实体类，汉堡是包在纸盒中，冷饮是装在瓶子中。
     * <p>
     * 然后我们创建一个 Meal 类，带有 Item 的 ArrayList 和一个通过结合 Item 来创建不同类型的 Meal 对象的 MealBuilder。
     * BuilderPatternDemo 类使用 MealBuilder 来创建一个 Meal。
     * <p>
     * 建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。
     * 这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
     * <p>
     * 一个 Builder 类会一步一步构造最终的对象。该 Builder 类是独立于其他对象的。
     */

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }
}
