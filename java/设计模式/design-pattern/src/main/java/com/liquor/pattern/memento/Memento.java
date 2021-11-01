package com.liquor.pattern.memento;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：16:20
 * Description：备忘录
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
