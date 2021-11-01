package com.liquor.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:13
 * Description：命令调用类
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Broker {

    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
