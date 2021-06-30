# spring

spring是一个开源应用框架，旨在降低应用程序开发的复杂度。

它是轻量级、松散耦合的。

它具有分层体系结构，允许用户选择组件，同时还为J2EE应用程序开发提供了一个有凝聚力的框架。

它可以集成其他框架，如strusts、hibernate、EJB等，所以又称为框架的框架。

# @Order注解源码解读

作用是定义Spring IOC容器中Bean的执行顺序的优先级，而不是定义Bean的加载顺序，Bean的加载顺序不受@Order或Ordered接口的影响；

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface Order {

	/**
	 * 默认是最低优先级,值越小优先级越高
	 */
	int value() default Ordered.LOWEST_PRECEDENCE;

}
```

- 注解可以作用在类(接口、枚举)、方法、字段声明（包括枚举常量）；
- 注解有一个int类型的参数，可以不传，默认是最低优先级；
- 通过常量类的值我们可以推测参数值越小优先级越高；

**Ordered接口类**

```java
package org.springframework.core;

public interface Ordered {
    int HIGHEST_PRECEDENCE = -2147483648;
    int LOWEST_PRECEDENCE = 2147483647;

    int getOrder();
}
```

