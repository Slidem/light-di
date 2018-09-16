package com.di.framework.testcomponents.packagec;

import com.di.framework.annotations.Component;
import com.di.framework.annotations.Inject;

import java.util.List;

/**
 * @author Mihai Alexandru
 * @date 27.08.2018
 */
@Component
public class ListDependency {

    @Inject
    private List<DummyClass> dummyClasses;

    public List<DummyClass> getDummyClasses() {
        return dummyClasses;
    }
}
