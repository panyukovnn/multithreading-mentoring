package ru.panyukovnn.multithreadingmentoring.theory.basics;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
public class Threadsafety {

    /**
     * Будет ли код в нижеприведенных методах потокобезопансым, если вызывать их параллельно из разных потоков?
     */

    public int countSymbols(String str, char c) {
        int counter = 0;

        for (char strC : str.toCharArray()) {
            if (strC == c) {
                counter++;
            }
        }

        return counter;
    }

    // ------------------------------------------------

    public List<Character> covertToCharList(String input) {
        List<Character> chars = new ArrayList<>();

        for (Character c : input.toCharArray()) {
            chars.add(c);
        }

        return chars;
    }

    // ---------------------- shared variable --------------------------

    private final List<String> storage = new ArrayList<>();

    public void collect(String record) throws ExecutionException, InterruptedException {
        if (!storage.contains(record)) {
            storage.add(record);
        }
    }

    // ---------------------- shared variable --------------------------

    public List<Integer> processWithMetrics() {
        List<Integer> metrics = new ArrayList<>();

        Thread thread1 = new Thread(() -> {
            metrics.add(1);

            // выполняю логику
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            metrics.add(2);

            // выполняю логику
        });
        thread2.start();

        return metrics;
    }
}
