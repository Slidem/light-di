package ami.lightdi.factories.impl;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import ami.lightdi.beans.classholders.ClassHolder;
import ami.lightdi.beans.classholders.impl.*;
import ami.lightdi.beans.framework.PrototypeFactory;
import ami.lightdi.exceptions.BeanNotFoundException;
import com.google.common.base.Strings;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public class ClassHolderFactory {

    /**
     * @param field
     * @return {@link ClassHolder} for the field.
     */
    public Optional<ClassHolder> getClassHolder(Field field) {
        if (!field.isAnnotationPresent(Inject.class)) {
            return Optional.empty();
        }

        var fieldClass = field.getType();
        String fieldBeanName = field.getAnnotation(Inject.class).value();

        if (List.class.isAssignableFrom(fieldClass)) {
            return Optional.of(getClassHolder(field.getGenericType(), ListClassHolder::new));
        }

        if (PrototypeFactory.class.isAssignableFrom(fieldClass)) {
            return Optional.of(getClassHolder(field.getGenericType(), PrototypeFactoryClassHolder::new));
        }

        return getFieldClassHolder(fieldClass, fieldBeanName);
    }

    /**
     * @param constructor
     * @return {@link ClassHolder}s for the given constructor.
     */
    public List<ClassHolder> getClassHolders(Constructor constructor) {
        if (!constructor.isAnnotationPresent(Inject.class)) {
            return Collections.emptyList();
        }
        var holders = new ArrayList<ClassHolder>();
        Type[] paramTypes = constructor.getGenericParameterTypes();
        for (Type type : paramTypes) {
            ClassHolder ch = null;
            if (type instanceof Class) {
                ch = getClassHolder((Class<?>) type).orElseThrow(() -> new BeanNotFoundException("Constructor required a bean of type: " + type + " but no annotated component found"));
            } else if (listType(type)) {
                ch = getClassHolder(type, ListClassHolder::new);
            } else if (objectFactoryType(type)) {
                ch = getClassHolder(type, PrototypeFactoryClassHolder::new);
            }
            ofNullable(ch).ifPresent(holders::add);
        }

        return holders;
    }

    /**
     * @param beanClass
     * @return {@link ClassHolder}s for the given class. Returns empty if the class is not {@link Component} annotated.
     */
    public Optional<ClassHolder> getClassHolder(Class<?> beanClass) {
        if (!beanClass.isAnnotationPresent(Component.class)) {
            return Optional.empty();
        }

        if (isNamedBean(beanClass)) {
            return Optional.of(new NamedBeanClassHolder(getBeanName(beanClass), beanClass));
        }

        return Optional.of(new DefaultClassHolder(beanClass));
    }

    // ----------------------------------------------------------------------------------------

    public Optional<ClassHolder> getFieldClassHolder(Class<?> beanClass, String beanName) {
        if (!Strings.isNullOrEmpty(beanName)) {
            return Optional.of(new FieldInjectNamedBeanClassHolder(beanName, beanClass));
        }

        if (!beanClass.isAnnotationPresent(Component.class)) {
            return Optional.empty();
        }

        if (isNamedBean(beanClass)) {
            return Optional.of(new NamedBeanClassHolder(getBeanName(beanClass), beanClass));
        }

        return Optional.of(new DefaultClassHolder(beanClass));
    }

    private ClassHolder getClassHolder(Type type, Function<Class<?>, ClassHolder> classHolderFunction) {
        ParameterizedType parameterizedListType = (ParameterizedType) type;
        Class<?> genericClassType = (Class<?>) parameterizedListType.getActualTypeArguments()[0];
        return classHolderFunction.apply(genericClassType);
    }

    private boolean listType(Type type) {
        return getRawType(type).map(List.class::isAssignableFrom).orElse(false);
    }

    private boolean objectFactoryType(Type type) {
        return getRawType(type).map(PrototypeFactory.class::isAssignableFrom).orElse(false);
    }

    private boolean isNamedBean(Class<?> fieldClass) {
        return !Strings.isNullOrEmpty(getBeanName(fieldClass));
    }


    private String getBeanName(Class<?> beanClass) {
        return beanClass.getAnnotation(Component.class).name();
    }

    private Optional<Class<?>> getRawType(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Optional.empty();
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        return Optional.of((Class<?>) parameterizedType.getRawType());
    }


}
