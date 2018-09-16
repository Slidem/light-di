package com.di.framework.exceptions;

/**
 * @author Mihai Alexandru
 * @date 22.08.2018
 */
public class BeanInstantiationException extends RuntimeException {

    public BeanInstantiationException(Throwable cause) {
        super(cause);
    }

    public BeanInstantiationException(String message) {
        super(message);
    }
}
