package com.liquor.writespring.spring;

/**
 * @Author: Liquor.Huang
 * @Date 2021/9/7 18:31
 */
public class BeanDefinition {

    private Class beanClass;

    private ScopeEnum scope;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public ScopeEnum getScope() {
        return scope;
    }

    public void setScope(ScopeEnum scope) {
        this.scope = scope;
    }
}
