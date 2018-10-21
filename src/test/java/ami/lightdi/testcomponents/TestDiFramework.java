package ami.lightdi.testcomponents;

import ami.lightdi.LightDI;
import ami.lightdi.testcomponents.packagea.Dummy;
import ami.lightdi.testcomponents.packageb.DummyComponentA;
import ami.lightdi.testcomponents.packageb.DummyComponentB;
import ami.lightdi.testcomponents.packageb.DummyComponentC;
import ami.lightdi.testcomponents.packageb.DummyComponentD;
import ami.lightdi.testcomponents.packagec.*;
import ami.lightdi.testcomponents.packagee.PrototypeA;
import ami.lightdi.testcomponents.packagee.PrototypeB;
import ami.lightdi.testcomponents.packagee.singletons.SingletonA;
import ami.lightdi.testcomponents.packagee.singletons.SingletonB;
import ami.lightdi.testcomponents.packagef.DummyConstructorInjections;
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
        LightDI.init("ami.lightdi.testcomponents.packagea");

        Dummy dummyBean = LightDI.getBean(Dummy.class);

        assertNotNull(dummyBean);
    }

    @Test
    public void testInjection() {
        //given
        LightDI.init("ami.lightdi.testcomponents.packageb");

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
        LightDI.init("ami.lightdi.testcomponents.packageb");

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
        LightDI.init("ami.lightdi.testcomponents.packagec");


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
        assertThrows(Exception.class, () -> LightDI.init("ami.lightdi.testcomponents.packaged"));
    }

    @Test
    public void testListDependency() {
        //given
        LightDI.init("ami.lightdi.testcomponents.packagec");

        ListDependencyConstructor listDependency = LightDI.getBean(ListDependencyConstructor.class);

        ListDependency listDependency1 = LightDI.getBean(ListDependency.class);

        listDependency.getClass();
    }

    @Test
    public void testBeanNameInjection() {

        LightDI.init("ami.lightdi.testcomponents.packagec");

        BeanNameInjection bni = LightDI.getBean(BeanNameInjection.class);

        assertNotNull(bni);
        assertNotNull(bni.getDummyClass());
        assertTrue(bni.getDummyClass() instanceof DummyClassC);
    }

    @Test
    public void testObjectFactory() {

        LightDI.init("ami.lightdi.testcomponents.packagee");

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

    @Test
    public void testConstructorInjection() {
        LightDI.init("ami.lightdi.testcomponents.packagef");
        DummyConstructorInjections dummyConstructorInjections = LightDI.getBean(DummyConstructorInjections.class);
        dummyConstructorInjections.toString();
    }


}
