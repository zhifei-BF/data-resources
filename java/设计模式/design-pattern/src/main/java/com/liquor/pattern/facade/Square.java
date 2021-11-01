package com.liquor.pattern.facade;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：11:26
 * Description：正方形
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
