package com.liquor.pattern.chain_of_responsibility;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：14:48
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
