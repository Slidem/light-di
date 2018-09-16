package com.di.framework.beans.visitors.impl;

import com.di.framework.beans.BeanHolder;
import com.di.framework.beans.BeanStore;
import com.di.framework.beans.classholders.ClassHolder;
import com.di.framework.beans.classholders.impl.*;
import com.di.framework.beans.visitors.ClassHolderVisitor;
import com.di.framework.factories.BeanHolderFactory;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Mihai Alexandru
 * @date 03.09.2018
 */
public class BeanInstanceHolderVisitor extends ClassHolderVisitor {

    private BeanHolder beanHolder;

    private BeanHolderFactory beanHolderFactory;

    private Supplier<Object> instanceSupplier;

    public BeanInstanceHolderVisitor(BeanStore beanStore, BeanHolderFactory beanHolderFactory, Supplier<Object> instanceSupplier) {
        super(beanStore);
        this.beanHolderFactory = beanHolderFactory;
        this.instanceSupplier = instanceSupplier;
    }

    protected BeanInstanceHolderVisitor(BeanStore beanStore) {
        super(beanStore);
    }

    @Override
    public void visit(ListClassHolder listClassHolder) {
        //do nothing.
    }

    @Override
    public void visit(FieldInjectNamedBeanClassHolder fieldInjectNamedBeanClassHolder) {
        //do nothing.
    }

    @Override
    public void visit(PrototypeFactoryClassHolder prototypeFactoryClassHolder) {
        //do nothing.
    }

    @Override
    public void visit(NamedBeanClassHolder namedBeanClassHolder) {
        initBeanHolder(namedBeanClassHolder);
    }

    @Override
    public void visit(DefaultClassHolder defaultClassHolder) {
        initBeanHolder(defaultClassHolder);
    }


    private void initBeanHolder(ClassHolder ch) {
        beanHolder = beanHolderFactory.getBeanHolder(ch.getBeanClass(), instanceSupplier);
    }


    public Optional<BeanHolder> getBeanHolder() {
        return Optional.ofNullable(beanHolder);
    }


}
