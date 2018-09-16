package ami.lightdi.factories.impl;

import ami.lightdi.annotations.Component;
import ami.lightdi.beans.BeanHolder;
import ami.lightdi.beans.impl.PrototypeHolder;
import ami.lightdi.beans.impl.SingletonHolder;
import ami.lightdi.factories.BeanHolderFactory;

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
