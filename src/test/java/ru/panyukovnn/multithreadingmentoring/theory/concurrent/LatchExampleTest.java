package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LatchExampleTest {

    private final LatchExample latchExample = new LatchExample();

    @Test
    void testLatch() throws InterruptedException {
        latchExample.demoLatch();
    }
}