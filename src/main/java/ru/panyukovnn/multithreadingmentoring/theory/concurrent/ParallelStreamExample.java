package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelStreamExample {

    private final List<Integer> list = List.of(0, 1, 2);

    public void demoParallelStream() {
        list.parallelStream()
            .map(i -> i + 1)
            .forEach(it -> System.out.println(Thread.currentThread().getName() + ": " + it));
    }

    public void demoParallelStreamInCustomExecutorService() {
        ExecutorService customForkJoinPool = Executors.newWorkStealingPool();

        CompletableFuture.runAsync(() -> {
                list.parallelStream()
                    .map(i -> i + 1)
                    .forEach(it -> System.out.println(Thread.currentThread().getName() + ": " + it));
            },
            customForkJoinPool);
    }
}
