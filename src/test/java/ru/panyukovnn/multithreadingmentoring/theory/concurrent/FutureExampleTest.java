package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class FutureExampleTest {

    private final FutureExample futureExample = new FutureExample();

    @Test
    void testFuture() throws ExecutionException, InterruptedException {
        futureExample.demoFuture();
    }

}