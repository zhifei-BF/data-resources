package com.liquor.pattern.singleton;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：16:13
 * Description：单例模式 懒汉式 线程安全
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Singleton2 {
    /**
     * 是否 Lazy 初始化：是
     * <p>
     * 是否多线程安全：是
     * <p>
     * 实现难度：易
     * <p>
     * 描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
     * 优点：第一次调用才初始化，避免内存浪费。
     * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
     * getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
     */
    private static Singleton2 instance;

    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
