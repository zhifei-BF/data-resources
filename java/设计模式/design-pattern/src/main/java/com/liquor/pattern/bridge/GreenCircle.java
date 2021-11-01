package com.liquor.pattern.bridge;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：9:57
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class GreenCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
            + radius + ", x: " + x + ", " + y + "]");
    }

}
