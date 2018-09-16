package com.di.framework.beans.classholders.impl;

import com.di.framework.beans.classholders.ClassHolder;
import com.di.framework.beans.visitors.ClassHolderVisitor;

/**
 * @author Mihai Alexandru
 * @date 08.09.2018
 */
public class PrototypeFactoryClassHolder implements ClassHolder {

    private Class<?> beanClass;

    public PrototypeFactoryClassHolder(Class<?> beanClass) {
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
}
