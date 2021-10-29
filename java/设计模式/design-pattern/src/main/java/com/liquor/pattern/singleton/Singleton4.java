package com.liquor.pattern.singleton;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：16:18
 * Description：双检锁/双重校验锁（DCL，即 double-checked locking）
 *
 * @author Liquor
 * @version 1.0.0
 */
public class Singleton4 {
    /**
     * JDK 版本：JDK1.5 起
     * <p>
     * 是否 Lazy 初始化：是
     * <p>
     * 是否多线程安全：是
     * <p>
     * 实现难度：较复杂
     * <p>
     * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     * getInstance() 的性能对应用程序很关键。
     */
    private volatile static Singleton4 singleton;

    private Singleton4() {
    }

    public static Singleton4 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
