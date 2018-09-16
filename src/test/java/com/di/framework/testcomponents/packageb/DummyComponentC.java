package com.di.framework.testcomponents.packageb;

import com.di.framework.annotations.Component;
import com.di.framework.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component
public class DummyComponentC {

    @Inject
    private DummyComponentA dummyComponentA;
}
