package com.di.framework.testcomponents.packagec;

import com.di.framework.annotations.Component;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
@Component(name = "dummyClassC")
public class DummyClassC implements DummyClass {
    @Override
    public String getText() {
        return "dummyC";
    }
}
