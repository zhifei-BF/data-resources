# Spring

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

# Spring缓存注解使用

## @Cacheable

## @CacheEvict

​	@CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。其中value、key和condition的语义与@Cacheable对应的属性类似。即value表示清除操作是发生在哪些Cache上的（对应Cache的名称）；key表示需要清除的是哪个key，如未指定则会使用默认策略生成的key；condition表示清除操作发生的条件。下面我们来介绍一下新出现的两个属性allEntries和beforeInvocation。

**allEntries属性**

​	allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。当指定了allEntries为true时，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。

```java
@CacheEvict(value="users", allEntries=true)
public void delete(Integer id) {
      System.out.println("delete user by id: " + id);
}
```

**beforeInvocation属性**

​	 清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。

```java
@CacheEvict(value="users", beforeInvocation=true)
public void delete(Integer id) {
      System.out.println("delete user by id: " + id);
}
```

## @CachePut

# @Primary 和 @Qualifier

​	当一个接口有多个不同实现时,使用@Autowired注解时会报org.springframework.beans.factory.NoUniqueBeanDefinitionException异常信息。

**解决**

（1）使用Qualifier注解，选择一个对象的名称,通常比较常用

（2）Primary可以理解为默认优先选择,不可以同时设置多个,内部实质是设置BeanDefinition的primary属性