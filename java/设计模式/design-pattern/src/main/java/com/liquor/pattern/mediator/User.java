package com.liquor.pattern.mediator;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：16:10
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }
}
