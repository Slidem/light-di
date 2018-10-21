package ami.lightdi.beans.visitors;

import ami.lightdi.beans.BeanStore;
import ami.lightdi.beans.classholders.impl.*;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public abstract class ClassHolderVisitor {

    protected BeanStore beanStore;

    protected ClassHolderVisitor(BeanStore beanStore) {
        this.beanStore = beanStore;
    }

    public abstract void visit(ListClassHolder listClassHolder);

    public abstract void visit(FieldInjectNamedBeanClassHolder fieldInjectNamedBeanClassHolder);

    public abstract void visit(ConstructorInjectClassHolder constructorInjectClassHolder);

    public abstract void visit(NamedBeanClassHolder namedBeanClassHolder);

    public abstract void visit(DefaultClassHolder defaultClassHolder);

    public abstract void visit(PrototypeFactoryClassHolder prototypeFactoryClassHolder);

}
