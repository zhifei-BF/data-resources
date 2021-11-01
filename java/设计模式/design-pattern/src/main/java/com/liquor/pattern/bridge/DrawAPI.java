package com.liquor.pattern.bridge;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：9:56
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */

/**
 * 画的API
 */
public interface DrawAPI {
    /**
     * 画圆
     *
     * @param radius
     * @param x
     * @param y
     */
    public void drawCircle(int radius, int x, int y);

}
