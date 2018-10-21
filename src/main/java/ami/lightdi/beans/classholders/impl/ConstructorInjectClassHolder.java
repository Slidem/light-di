package ami.lightdi.beans.classholders.impl;

import ami.lightdi.beans.classholders.ClassHolder;
import ami.lightdi.beans.visitors.ClassHolderVisitor;

/**
 * @author Mihai Alexandru
 * @date 21.10.2018
 */
public class ConstructorInjectClassHolder implements ClassHolder {

    private Class<?> beanClass;

    public ConstructorInjectClassHolder(Class<?> beanClass) {
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
