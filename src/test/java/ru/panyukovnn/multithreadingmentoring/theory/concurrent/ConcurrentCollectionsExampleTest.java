package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConcurrentCollectionsExampleTest {

    private final ConcurrentCollectionsExample concurrentCollectionsExample = new ConcurrentCollectionsExample();

    @Test
    void testThrowsConcurrentModificationException() {
        assertThrows(
            ConcurrentModificationException.class,
            () -> concurrentCollectionsExample.throwsConcurrentModificationException()
        );
    }

    @Test
    void testNoConcurrentModificationException() {
        concurrentCollectionsExample.noConcurrentModificationException();
    }
}