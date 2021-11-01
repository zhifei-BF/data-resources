package com.liquor.pattern.interpreter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:30
 * Description：表达式接口
 *
 * @author Liquor
 * @version 1.0.0
 */
public interface Expression {
    public boolean interpret(String context);
}
