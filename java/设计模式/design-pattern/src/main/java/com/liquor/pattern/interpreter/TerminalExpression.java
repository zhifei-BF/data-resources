package com.liquor.pattern.interpreter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:30
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data)) {
            return true;
        }
        return false;
    }
}
