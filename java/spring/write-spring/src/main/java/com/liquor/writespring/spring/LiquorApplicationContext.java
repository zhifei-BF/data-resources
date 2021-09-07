package com.liquor.writespring.spring;

import java.lang.annotation.Annotation;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/7 18:23
 */
public class LiquorApplicationContext {


    public LiquorApplicationContext(Class configClass) {
        scan(configClass);
    }

    private void scan(Class configClass) {
        // 扫描class，转化为BeanDefinition对象，最后添加到beanDefinitionMap中

        // 先得到包路径
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String packagePath = annotation.value();



    }

    public Object getBean(String beanName) {
        return null;
    }
}
