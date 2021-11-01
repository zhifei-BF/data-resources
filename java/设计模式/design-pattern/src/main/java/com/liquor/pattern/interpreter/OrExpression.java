package com.liquor.pattern.interpreter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:31
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class OrExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}
