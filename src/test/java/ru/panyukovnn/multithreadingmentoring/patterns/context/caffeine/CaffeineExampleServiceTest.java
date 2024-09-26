package ru.panyukovnn.multithreadingmentoring.patterns.context.caffeine;

import org.junit.jupiter.api.Test;
import ru.panyukovnn.multithreadingmentoring.AbstractTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CaffeineExampleServiceTest extends AbstractTest {

    @Test
    void testProcess() {
        long startTime = System.currentTimeMillis();
        caffeineExampleService.process("some request");
        assertTrue(System.currentTimeMillis() - startTime > 1000);

        startTime = System.currentTimeMillis();
        caffeineExampleService.process("some request");
        assertTrue(System.currentTimeMillis() - startTime < 1000);
    }
}