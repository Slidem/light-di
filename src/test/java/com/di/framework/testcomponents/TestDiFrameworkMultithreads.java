package com.di.framework.testcomponents;

import com.di.framework.LightDI;
import com.di.framework.testcomponents.packagee.PrototypeA;
import com.di.framework.testcomponents.packagee.PrototypeB;
import com.di.framework.testcomponents.packagee.prototypes.ProtB;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Mihai Alexandru
 * @date 14.09.2018
 */
public class TestDiFrameworkMultithreads {

    @Test
    public void test_multiple_threads_initialization() {
        ExecutorService es = Executors.newFixedThreadPool(5);

        List<Runnable> tasks = List.of(
                () -> LightDI.init("com.di.framework.testcomponents.packagee"),
                () -> assertNotNull(LightDI.getBean(PrototypeA.class)),
                () -> assertNotNull(LightDI.getBean(PrototypeB.class)),
                () -> assertNotNull(LightDI.getBean(ProtB.class)),
                () -> assertNotNull(LightDI.getBean(ProtB.class))
        );
        tasks.stream().forEach(es::execute);
    }

}
