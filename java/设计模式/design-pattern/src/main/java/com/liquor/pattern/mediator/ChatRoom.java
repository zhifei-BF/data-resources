package com.liquor.pattern.mediator;

import java.util.Date;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：16:10
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class ChatRoom {

    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString()
            + " [" + user.getName() + "] : " + message);
    }
}
