package com.liquor.pattern.abstract_factory;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：16:53
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
