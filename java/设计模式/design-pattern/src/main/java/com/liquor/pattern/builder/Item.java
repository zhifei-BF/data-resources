package com.liquor.pattern.builder;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:04
 * Description：商品
 *
 * @author Liquor
 * @version 1.0.0
 */
public interface Item {

    public String name();

    //包装
    public Packing packing();

    //价格
    public float price();
}
