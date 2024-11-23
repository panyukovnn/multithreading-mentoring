package ru.panyukovnn.multithreadingmentoring.theory.basics;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class PossibleReorderingTest {

    @Test
    void possibleReordering() {
        Map<PossibleReordering.Pair, Integer> results = new HashMap<>();

        for (int i = 0; i < 500_000; i++) {
            PossibleReordering possibleReordering = new PossibleReordering();

            PossibleReordering.Pair pair = possibleReordering.tryReorderingOperations();

            results.merge(pair, 1, Integer::sum);
        }

        results.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}