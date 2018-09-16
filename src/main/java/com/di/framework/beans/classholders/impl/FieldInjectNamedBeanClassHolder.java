package com.di.framework.beans.classholders.impl;

import com.di.framework.beans.classholders.ClassHolder;
import com.di.framework.beans.visitors.ClassHolderVisitor;

/**
 * @author Mihai Alexandru
 * @date 15.09.2018
 */
public class FieldInjectNamedBeanClassHolder implements ClassHolder {

    private String beanName;

    private Class<?> beanClass;

    public FieldInjectNamedBeanClassHolder(String beanName, Class<?> beanClass) {
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
