package com.di.framework.beans.visitors.impl;

import com.di.framework.beans.BeanStore;
import com.di.framework.beans.classholders.impl.*;
import com.di.framework.beans.framework.PrototypeFactory;
import com.di.framework.beans.visitors.ClassHolderVisitor;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public class BeanStoreInstanceVisitor extends ClassHolderVisitor {

    private Object instance;

    private Reflections reflections;

    public BeanStoreInstanceVisitor(BeanStore beanStore, Reflections reflections) {
        super(beanStore);
        this.reflections = reflections;
    }

    @Override
    public void visit(ListClassHolder listClassHolder) {
        var subtypes = reflections.getSubTypesOf(listClassHolder.getGenericTypeClass());
        boolean listDependenciesInitialized = subtypes.stream().map(beanStore::getBean).allMatch(Optional::isPresent);
        if (listDependenciesInitialized) {
            instance = new ArrayList<>(beanStore.getBeans(listClassHolder.getBeanClass()));
        }
    }

    @Override
    public void visit(FieldInjectNamedBeanClassHolder fieldInjectNamedBeanClassHolder) {
        instance = beanStore.getBean(fieldInjectNamedBeanClassHolder.getBeanName(), fieldInjectNamedBeanClassHolder.getBeanClass()).orElse(null);
    }

    @Override
    public void visit(PrototypeFactoryClassHolder prototypeFactoryClassHolder) {
        Optional<?> factoryBean = beanStore.getBean(prototypeFactoryClassHolder.getBeanClass());
        if (factoryBean.isPresent()) {
            instance = new PrototypeFactory<>(() -> beanStore.getBean(prototypeFactoryClassHolder.getBeanClass()).orElse(null));
        }
    }

    @Override
    public void visit(NamedBeanClassHolder namedBeanClassVisitor) {
        String beanName = namedBeanClassVisitor.getBeanName();
        instance = beanStore.getBean(beanName, namedBeanClassVisitor.getBeanClass()).orElse(null);
    }

    @Override
    public void visit(DefaultClassHolder defaultClassHolder) {
        instance = beanStore.getBean(defaultClassHolder.getBeanClass()).orElse(null);
    }

    public Optional<Object> getInstance() {
        return Optional.ofNullable(instance);
    }
}
