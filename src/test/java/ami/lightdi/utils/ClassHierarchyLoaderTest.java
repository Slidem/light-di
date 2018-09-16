package ami.lightdi.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mihai Alexandru
 * @date 18.08.2018
 */
public class ClassHierarchyLoaderTest {

    @ParameterizedTest
    @MethodSource(value = "hierarchyParameterSource")
    public void testHierarchyLoaderEmpty(int size, Class<?> classToTest, List<Class<?>> expectedHierarchyClasses) {
        //when
        Set<Class<?>> result = ClassHierarchyLoader.getParents(classToTest);
        //expect
        assertNotNull(result);
        assertEquals(size, result.size());
        for (Class<?> c : expectedHierarchyClasses) {
            assertTrue(result.contains(c));
        }
    }

    private static Stream<Arguments> hierarchyParameterSource() {

        return Stream.of(
                Arguments.of(1, TestClass.class, List.of(TestClass.class)),
                Arguments.of(2, WithParent.class, List.of(WithParent.class, Parent.class)),
                Arguments.of(7, MultipleParents.class, List.of(IEX.class, IB.class, IC.class, IA.class, WithParent.class, Parent.class, MultipleParents.class))
        );

    }

    // ---------- Test classes ---------------

    private static class TestClass {
    }

    private static class Parent {
    }

    private static class WithParent extends Parent {
    }

    private static interface IA {
    }

    private static interface IB {
    }

    private static interface IC {
    }

    private static interface IEX extends IB, IC {
    }

    private static class MultipleParents extends WithParent implements IA, IEX {
    }


}
