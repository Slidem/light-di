package com.di.framework.testcomponents.packageb;

import com.di.framework.annotations.Component;
import com.di.framework.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component(name = "myDummyComponentB")
public class DummyComponentB {

    @Inject
    private DummyComponentA componentA;

    @Inject
    private DummyComponentC componentC;

    public DummyComponentA getComponentA() {
        return componentA;
    }

    public DummyComponentC getComponentC() {
        return componentC;
    }
}
