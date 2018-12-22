package ami.lightdi.beans.visitors.impl;

import ami.lightdi.annotations.Inject;
import ami.lightdi.beans.BeanStore;
import ami.lightdi.beans.classholders.ClassHolder;
import ami.lightdi.beans.classholders.impl.*;
import ami.lightdi.beans.framework.PrototypeFactory;
import ami.lightdi.beans.visitors.ClassHolderVisitor;
import ami.lightdi.exceptions.BeanInstantiationException;
import ami.lightdi.exceptions.BeanNotFoundException;
import ami.lightdi.factories.impl.ClassHolderFactory;
import ami.lightdi.utils.ClassHierarchyLoader;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Mihai Alexandru
 * @date 01.09.2018
 */
public class BeanInstanceVisitor extends ClassHolderVisitor {

    private ClassHolderFactory classHolderFactory;

    private Supplier<Object> instanceSupplier;

    public BeanInstanceVisitor(BeanStore beanStore, ClassHolderFactory classHolderFactory) {
        super(beanStore);
        this.classHolderFactory = classHolderFactory;
    }

    @Override
    public void visit(ListClassHolder listClassHolder) {
        var beanClass = listClassHolder.getGenericTypeClass();
        var beans = beanStore.getBeans(beanClass);
        instanceSupplier = () -> (new ArrayList<>(beans));
    }

    @Override
    public void visit(PrototypeFactoryClassHolder prototypeFactoryClassHolder) {
        var beanClass = prototypeFactoryClassHolder.getBeanClass();
        Supplier<?> beanSupplier = () -> beanStore.getBean(beanClass).orElseThrow(() -> new BeanNotFoundException(beanClass));
        instanceSupplier = () -> (new PrototypeFactory<>(beanSupplier));
    }

    @Override
    public void visit(FieldInjectNamedBeanClassHolder fieldInjectNamedBeanClassHolder) {
        String beanName = fieldInjectNamedBeanClassHolder.getBeanName();
        instanceSupplier = () -> beanStore.getBean(beanName, fieldInjectNamedBeanClassHolder.getBeanClass()).orElseThrow(() -> new BeanNotFoundException(beanName));
    }

    @Override
    public void visit(ConstructorInjectClassHolder constructorInjectClassHolder) {
        instanceSupplier = () -> beanStore.getBean(constructorInjectClassHolder.getBeanClass()).orElseThrow(() -> new BeanNotFoundException(constructorInjectClassHolder.getBeanClass().getSimpleName()));
    }

    @Override
    public void visit(NamedBeanClassHolder namedBeanClassVisitor) {
        initBean(namedBeanClassVisitor.getBeanClass());
    }


    @Override
    public void visit(DefaultClassHolder defaultClassHolder) {
        initBean(defaultClassHolder.getBeanClass());
    }


    public Supplier<Object> getInstanceSupplier() {
        return instanceSupplier;
    }


    // ---------------------------------------------------------------------------


    private void initBean(Class<?> componentClass) {
        instanceSupplier = () -> {
            Object beanInstance = initObject(componentClass);
            initFields(beanInstance);
            return beanInstance;
        };
    }

    @SuppressWarnings("unchecked")
    private Object initObject(Class<?> componentClass) {
        Set<Constructor> constructors = ReflectionUtils.getConstructors(componentClass);
        if (onlyOneConstructorWithNoParameters(constructors)) {
            return createInstanceFromConstructor(constructors);
        }
        if (onlyOneConstructorWithMultipleParams(constructors)) {
            Constructor<?> constructor = constructors.iterator().next();
            checkInjectAnnotationPresent(constructor);
            return createInstanceFromConstructor(constructor, classHolderFactory.getClassHolders(constructor));
        }
        throw new BeanInstantiationException("No valid constructors found.");
    }

    private void initFields(Object instance) {
        for (var clazz : ClassHierarchyLoader.getParents(instance.getClass())) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                classHolderFactory.getClassHolder(f).ifPresent(ch -> {
                    Object fieldBean = beanStore.getBean(ch).orElseThrow(() -> new BeanNotFoundException(ch.getBeanClass()));
                    setFieldValue(f, instance, fieldBean);
                });
            }
        }
    }

    private boolean onlyOneConstructorWithNoParameters(Set<Constructor> constructors) {
        return constructors.size() == 1 && constructors.iterator().next().getParameterCount() == 0;
    }

    private boolean onlyOneConstructorWithMultipleParams(Set<Constructor> constructors) {
        return constructors.size() == 1 && constructors.iterator().next().getParameterCount() != 0;
    }

    private void checkInjectAnnotationPresent(Constructor<?> constructor) {
        if (!constructor.isAnnotationPresent(Inject.class)) {
            throw new BeanInstantiationException("No Inject annotation found on the constructor");
        }
    }

    private Object createInstanceFromConstructor(Set<Constructor> constructors) {
        try {
            return constructors.iterator().next().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeanInstantiationException(e);
        }
    }

    private Object createInstanceFromConstructor(Constructor constructor, List<ClassHolder> params) {
        List<Object> contructorParamsInstances = params
                .stream()
                .map(ch -> beanStore.getBean(ch).orElseThrow(() -> new BeanNotFoundException(ch.getBeanClass())))
                .collect(Collectors.toList());
        try {
            return constructor.newInstance(contructorParamsInstances.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeanInstantiationException(e);
        }
    }

    private void setFieldValue(Field f, Object obj, Object fieldInstance) {
        f.setAccessible(true);
        try {
            f.set(obj, fieldInstance);
        } catch (IllegalAccessException e) {
            throw new BeanInstantiationException(e);
        }
    }


}
