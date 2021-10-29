package com.liquor.pattern.singleton;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：16:11
 * Description：单例模式 懒汉式  线程不安全
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Singleton1 {
    /**
     * 是否 Lazy 初始化：是
     * <p>
     * 是否多线程安全：否
     * <p>
     * 实现难度：易
     * <p>
     * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
     * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
     */
    private static Singleton1 instance;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
