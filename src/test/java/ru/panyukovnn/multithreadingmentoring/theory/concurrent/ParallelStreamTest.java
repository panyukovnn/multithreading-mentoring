package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import org.junit.jupiter.api.Test;

class ParallelStreamTest {

    private final ParallelStreamExample parallelStreamExample = new ParallelStreamExample();

    @Test
    void testParallelStream() {
        parallelStreamExample.demoParallelStream();
    }

    @Test
    void testParallelStreamInCustomExecutorService() {
        parallelStreamExample.demoParallelStreamInCustomExecutorService();
    }
}