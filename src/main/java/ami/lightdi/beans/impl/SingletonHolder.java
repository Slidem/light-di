package ami.lightdi.beans.impl;

import ami.lightdi.beans.BeanHolder;

/**
 * @author Mihai Alexandru
 * @date 18.08.2018
 */
public class SingletonHolder implements BeanHolder {

    private Object instance;

    public SingletonHolder(Object instance) {
        this.instance = instance;
    }

    @Override
    public Object get() {
        return instance;
    }
}
