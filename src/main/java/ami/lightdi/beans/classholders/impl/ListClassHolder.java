package ami.lightdi.beans.classholders.impl;

import ami.lightdi.beans.classholders.ClassHolder;
import ami.lightdi.beans.visitors.ClassHolderVisitor;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public class ListClassHolder implements ClassHolder {

    private Class<?> genericTypeClass;

    public ListClassHolder(Class<?> genericTypeClass) {
        this.genericTypeClass = genericTypeClass;
    }

    @Override
    public void accept(ClassHolderVisitor classHolderVisitor) {
        classHolderVisitor.visit(this);
    }

    @Override
    public Class<?> getBeanClass() {
        return getGenericTypeClass();
    }

    public Class<?> getGenericTypeClass() {
        return genericTypeClass;
    }


}
