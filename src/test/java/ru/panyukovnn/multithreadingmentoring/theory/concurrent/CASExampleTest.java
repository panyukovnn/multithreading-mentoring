package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import org.junit.jupiter.api.Test;

class CASExampleTest {

    private final CASExample casExample = new CASExample();

    @Test
    void when_callCas_then_success() {
        casExample.callCas();
    }
}