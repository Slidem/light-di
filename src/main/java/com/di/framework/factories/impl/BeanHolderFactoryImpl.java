package com.di.framework.factories.impl;

import com.di.framework.annotations.Component;
import com.di.framework.beans.BeanHolder;
import com.di.framework.beans.impl.PrototypeHolder;
import com.di.framework.beans.impl.SingletonHolder;
import com.di.framework.factories.BeanHolderFactory;

import java.util.function.Supplier;

/**
 * @author Mihai Alexandru
 * @date 22.08.2018
 */
public class BeanHolderFactoryImpl implements BeanHolderFactory {

    @Override
    public BeanHolder getBeanHolder(Class<?> clazz, Supplier<?> instanceSupplier) {
        Component component = clazz.getAnnotation(Component.class);
        switch (component.scope()) {
            case SINGLETON:
                return new SingletonHolder(instanceSupplier.get());
            case PROTOTYPE:
                return new PrototypeHolder(instanceSupplier);
            default:
                throw new IllegalArgumentException("No factory found for the given class: " + clazz.getSimpleName());
        }
    }
}
