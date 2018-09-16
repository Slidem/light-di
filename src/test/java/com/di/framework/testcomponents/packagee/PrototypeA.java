package com.di.framework.testcomponents.packagee;

import com.di.framework.annotations.Component;
import com.di.framework.annotations.Inject;
import com.di.framework.annotations.Scope;
import com.di.framework.testcomponents.packagee.prototypes.ProtA;
import com.di.framework.testcomponents.packagee.prototypes.ProtB;

/**
 * @author Mihai Alexandru
 * @date 08.09.2018
 */
@Component(scope = Scope.PROTOTYPE)
public class PrototypeA {

    @Inject
    private ProtA protA;

    @Inject
    private ProtB protB;

    public ProtA getProtA() {
        return protA;
    }

    public ProtB getProtB() {
        return protB;
    }
}
