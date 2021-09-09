package com.liquor.springdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/9 17:24
 */
@ComponentScan(value = "com.liquor.springdemo",
        //排除
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = UserService.class
        )},
        includeFilters = {@ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = UserService.class
        )
        })
public class AppConfig2 {
}
