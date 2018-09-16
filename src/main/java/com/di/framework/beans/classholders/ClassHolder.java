package com.di.framework.beans.classholders;

import com.di.framework.beans.visitors.ClassHolderVisitor;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public interface ClassHolder {

    void accept(ClassHolderVisitor classHolderVisitor);

    Class<?> getBeanClass();
}
