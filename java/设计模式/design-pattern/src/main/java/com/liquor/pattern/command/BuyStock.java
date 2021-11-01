package com.liquor.pattern.command;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:13
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class BuyStock implements Order {

    private Stock abcStock;

    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}
