package com.liquor.pattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：16:23
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class CareTaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
