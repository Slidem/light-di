package ami.lightdi.factories;

import ami.lightdi.beans.BeanHolder;

import java.util.function.Supplier;

/**
 * @author Mihai Alexandru
 * @date 22.08.2018
 */
public interface BeanHolderFactory {

    BeanHolder getBeanHolder(Class<?> clazz, Supplier<?> instanceSupplier);
}
