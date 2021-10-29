package com.liquor.pattern.singleton;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：16:20
 * Description：枚举
 *
 * @author Liquor
 * @version 1.0.0
 */
public enum Singleton6 {
    /**
     * JDK 版本：JDK1.5 起
     *
     * 是否 Lazy 初始化：否
     *
     * 是否多线程安全：是
     *
     * 实现难度：易
     *
     * 描述：这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
     * 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，
     * 防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
     * 不能通过 reflection attack 来调用私有构造方法。
     */

    INSTANCE;

    public void whateverMethod() {
    }

    /**
     * 经验之谈：一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。只有在要明确实现 lazy loading 效果时，
     * 才会使用第 5 种登记方式。如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。
     */
}

