package ami.lightdi.beans.classholders.impl;

import ami.lightdi.beans.classholders.ClassHolder;
import ami.lightdi.beans.visitors.ClassHolderVisitor;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public class DefaultClassHolder implements ClassHolder {

    private Class<?> beanClass;

    public DefaultClassHolder(Class<?> beanClass) {
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
