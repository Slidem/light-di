package com.di.framework.testcomponents.packagee.singletons;

import com.di.framework.annotations.Component;
import com.di.framework.annotations.Inject;
import com.di.framework.annotations.Scope;
import com.di.framework.testcomponents.packagee.PrototypeA;
import com.di.framework.testcomponents.packagee.PrototypeB;

/**
 * @author Mihai Alexandru
 * @date 08.09.2018
 */
@Component(scope = Scope.SINGLETON)
public class SingletonA {

    private PrototypeA prototypeA;

    private PrototypeB prototypeB;

    @Inject
    public SingletonA(PrototypeA prototypeA, PrototypeB prototypeB) {
        this.prototypeA = prototypeA;
        this.prototypeB = prototypeB;
    }

    public PrototypeA getPrototypeA() {
        return prototypeA;
    }

    public PrototypeB getPrototypeB() {
        return prototypeB;
    }
}
