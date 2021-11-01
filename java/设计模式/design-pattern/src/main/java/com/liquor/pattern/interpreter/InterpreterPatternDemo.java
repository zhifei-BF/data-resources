package com.liquor.pattern.interpreter;

/**
 * Project：data-resources
 * Date：2021/11/1
 * Time：15:32
 * Description：
 *
 * @author Liquor
 * @version 1.0.0
 */
public class InterpreterPatternDemo {
    /**
     * 解释器模式（Interpreter Pattern）提供了评估语言的语法或表达式的方式，它属于行为型模式。
     * 这种模式实现了一个表达式接口，该接口解释一个特定的上下文。这种模式被用在 SQL 解析、符号处理引擎等。
     * <p>
     * 我们将创建一个接口 Expression 和实现了 Expression 接口的实体类。定义作为上下文中主要解释器的 TerminalExpression 类。
     * 其他的类 OrExpression、AndExpression 用于创建组合式表达式。
     * <p>
     * InterpreterPatternDemo，我们的演示类使用 Expression 类创建规则和演示表达式的解析。
     */


    //规则：Robert 和 John 是男性
    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    public static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? "
            + isMarriedWoman.interpret("Married Julie"));
    }
}
