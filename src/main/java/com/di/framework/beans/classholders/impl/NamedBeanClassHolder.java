package com.di.framework.beans.classholders.impl;

import com.di.framework.beans.classholders.ClassHolder;
import com.di.framework.beans.visitors.ClassHolderVisitor;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public class NamedBeanClassHolder implements ClassHolder {

    private String beanName;

    private Class<?> beanClass;

    public NamedBeanClassHolder(String beanName, Class<?> beanClass) {
        this.beanName = beanName;
        this.beanClass = beanClass;
    }

    @Override
    public void accept(ClassHolderVisitor classHolderVisitor) {
        classHolderVisitor.visit(this);
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    public String getBeanName() {
        return beanName;
    }
}
