package com.di.framework.testcomponents;

import com.di.framework.LightDI;
import com.di.framework.testcomponents.packagea.Dummy;
import com.di.framework.testcomponents.packageb.DummyComponentA;
import com.di.framework.testcomponents.packageb.DummyComponentB;
import com.di.framework.testcomponents.packageb.DummyComponentC;
import com.di.framework.testcomponents.packageb.DummyComponentD;
import com.di.framework.testcomponents.packagec.*;
import com.di.framework.testcomponents.packagee.PrototypeA;
import com.di.framework.testcomponents.packagee.PrototypeB;
import com.di.framework.testcomponents.packagee.singletons.SingletonA;
import com.di.framework.testcomponents.packagee.singletons.SingletonB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mihai Alexandru
 * @date 26.08.2018
 */
public class TestDiFramework {

    @AfterEach
    public void after() {
        LightDI.reset();
    }

    @Test
    public void testDummyComponent() {
        //given
        LightDI.init("com.di.framework.testcomponents.packagea");

        Dummy dummyBean = LightDI.getBean(Dummy.class);

        assertNotNull(dummyBean);
    }

    @Test
    public void testInjection() {
        //given
        LightDI.init("com.di.framework.testcomponents.packageb");

        //when
        DummyComponentA ca = LightDI.getBean(DummyComponentA.class);
        DummyComponentB cb = LightDI.getBean(DummyComponentB.class);
        DummyComponentC cc = LightDI.getBean(DummyComponentC.class);
        DummyComponentD cd = LightDI.getBean(DummyComponentD.class);

        //expect
        assertNotNull(ca);
        assertNotNull(cb);
        assertNotNull(cc);
        assertNotNull(cd);

        assertSame(ca, cb.getComponentA());
        assertSame(cc, cb.getComponentC());
        assertSame(ca, cd.getCa());
        assertSame(cb, cd.getCb());
        assertSame(cc, cd.getCc());
    }


    @Test
    public void testBeanName() {

        //given
        LightDI.init("com.di.framework.testcomponents.packageb");

        //when
        DummyComponentB cb = LightDI.getBean("myDummyComponentB", DummyComponentB.class);
        DummyComponentD cd = LightDI.getBean("myDummyComponentD", DummyComponentD.class);

        //expect

        assertNotNull(cb);
        assertNotNull(cd);
        assertNotNull(cd.getCa());
        assertNotNull(cd.getCb());
        assertNotNull(cd.getCc());
    }

    @Test
    public void testMultipleBeans() {
        //given
        LightDI.init("com.di.framework.testcomponents.packagec");


        List<DummyClass> dummies = LightDI.getBeans(DummyClass.class);

        assertNotNull(dummies);
        assertEquals(3, dummies.size());
        Set<String> texts = dummies.stream().map(DummyClass::getText).collect(Collectors.toSet());
        assertTrue(texts.contains("dummyA"));
        assertTrue(texts.contains("dummyB"));
        assertTrue(texts.contains("dummyC"));

    }

    @Test
    public void testCircularDependency() {
        assertThrows(Exception.class, () -> LightDI.init("com.di.framework.testcomponents.packaged"));
    }

    @Test
    public void testListDependency() {
        //given
        LightDI.init("com.di.framework.testcomponents.packagec");

        ListDependencyConstructor listDependency = LightDI.getBean(ListDependencyConstructor.class);

        ListDependency listDependency1 = LightDI.getBean(ListDependency.class);

        listDependency.getClass();
    }

    @Test
    public void testBeanNameInjection() {

        LightDI.init("com.di.framework.testcomponents.packagec");

        BeanNameInjection bni = LightDI.getBean(BeanNameInjection.class);

        assertNotNull(bni);
        assertNotNull(bni.getDummyClass());
        assertTrue(bni.getDummyClass() instanceof DummyClassC);
    }

    @Test
    public void testObjectFactory() {

        LightDI.init("com.di.framework.testcomponents.packagee");

        SingletonA singletonA = LightDI.getBean(SingletonA.class);

        SingletonB singletonB = LightDI.getBean(SingletonB.class);

        PrototypeA pa = singletonA.getPrototypeA();
        PrototypeB pb = singletonA.getPrototypeB();

        PrototypeA paA = singletonB.getPrototypeFactoryA();
        PrototypeB paB = singletonB.getPrototypeFactoryB();

        PrototypeA paAA = singletonB.getPrototypeFactoryA();
        PrototypeB paBB = singletonB.getPrototypeFactoryB();

        assertSame(singletonA.getPrototypeA(), singletonA.getPrototypeA());
        assertNotSame(singletonA.getPrototypeA(), singletonB.getPrototypeFactoryB());
        assertNotSame(paA, paAA);
        assertNotSame(paB, paBB);
        assertNotSame(paA.getProtA(), paAA.getProtA());
    }


}
