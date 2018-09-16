package ami.lightdi.objects;

import ami.lightdi.annotations.Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Mihai Alexandru
 * @date 18.08.2018
 */
public class BeanKeyTest {

    @Test
    public void testBeanKeyName() {
        //when
        BeanNameKey key = new BeanNameKey(TestComponent.class);
        //expect
        Assertions.assertEquals("test name", key.getName());
    }

    @Test
    public void testBeanNoNameKey() {
        //when
        BeanNameKey key = new BeanNameKey(TestComponentNoName.class);
        //expect
        Assertions.assertEquals("testComponentNoName", key.getName());
    }

    @Component(name = "test name")
    private static class TestComponent {

    }

    @Component
    private static class TestComponentNoName {

    }


}
