package com.di.framework.testcomponents.packagec;

import com.di.framework.annotations.Component;
import com.di.framework.annotations.Inject;

/**
 * @author Mihai Alexandru
 * @date 15.09.2018
 */
@Component
public class BeanNameInjection {

    @Inject("dummyClassC")
    private DummyClass dummyClass;

    public DummyClass getDummyClass() {
        return dummyClass;
    }
}
