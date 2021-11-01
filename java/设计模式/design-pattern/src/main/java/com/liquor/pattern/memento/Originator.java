package com.liquor.pattern.memento;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：16:20
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento) {
        state = Memento.getState();
    }
}
