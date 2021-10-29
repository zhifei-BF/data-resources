package com.liquor.pattern.builder;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:08
 * Description：冷饮
 *
 * @author Liquor
 * @version 1.0.0
 */
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
