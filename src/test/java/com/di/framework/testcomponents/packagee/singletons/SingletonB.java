package com.di.framework.testcomponents.packagee.singletons;

import com.di.framework.annotations.Component;
import com.di.framework.annotations.Inject;
import com.di.framework.beans.framework.PrototypeFactory;
import com.di.framework.testcomponents.packagee.PrototypeA;
import com.di.framework.testcomponents.packagee.PrototypeB;

/**
 * @author Mihai Alexandru
 * @date 08.09.2018
 */
@Component
public class SingletonB {

    private PrototypeFactory<PrototypeA> prototypeFactoryA;

    private PrototypeFactory<PrototypeB> prototypeFactoryB;

    @Inject
    public SingletonB(PrototypeFactory<PrototypeA> prototypeFactoryA, PrototypeFactory<PrototypeB> prototypeFactoryB) {
        this.prototypeFactoryA = prototypeFactoryA;
        this.prototypeFactoryB = prototypeFactoryB;
    }

    public PrototypeA getPrototypeFactoryA() {
        return prototypeFactoryA.get();
    }

    public PrototypeB getPrototypeFactoryB() {
        return prototypeFactoryB.get();
    }
}
