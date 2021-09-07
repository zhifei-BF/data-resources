package com.liquor.writespring.spring;

import java.lang.annotation.*;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/7 18:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    String value() default "";
}
