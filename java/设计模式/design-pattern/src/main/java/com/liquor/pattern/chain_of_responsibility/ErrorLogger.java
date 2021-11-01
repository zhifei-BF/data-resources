package com.liquor.pattern.chain_of_responsibility;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：14:49
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

