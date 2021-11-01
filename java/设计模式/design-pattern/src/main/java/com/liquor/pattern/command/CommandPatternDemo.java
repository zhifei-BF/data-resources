package com.liquor.pattern.command;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:14
 * Description：命令模式
 *
 * @author Liquor
 * @version 1.0.0
 */
public class CommandPatternDemo {
    /**
     * 命令模式（Command Pattern）是一种数据驱动的设计模式，它属于行为型模式。请求以命令的形式包裹在对象中，并传给调用对象。
     * 调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令。
     * <p>
     * 我们首先创建作为命令的接口 Order，然后创建作为请求的 Stock 类。实体命令类 BuyStock 和 SellStock，
     * 实现了 Order 接口，将执行实际的命令处理。创建作为调用对象的类 Broker，它接受订单并能下订单。
     * <p>
     * Broker 对象使用命令模式，基于命令的类型确定哪个对象执行哪个命令。CommandPatternDemo 类使用 Broker 类来演示命令模式。
     */


    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }

}
