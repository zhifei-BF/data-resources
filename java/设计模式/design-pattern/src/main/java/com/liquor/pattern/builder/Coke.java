package com.liquor.pattern.builder;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:10
 * Description：可口可乐
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Coke extends ColdDrink {
    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
