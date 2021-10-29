package com.liquor.pattern.builder;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:06
 * Description：瓶子
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
