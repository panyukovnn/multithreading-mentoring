package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.panyukovnn.multithreadingmentoring.util.MentoringUtil.sleep;

public class CASExample {

    private static final int MAX_CONNECTIONS = 100;

    private final AtomicInteger activeConnections = new AtomicInteger();

    @SneakyThrows
    public void callCas() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 110; i++) {
            executorService.submit(this::tryConnect);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("result: " + activeConnections.get());
    }

    private boolean tryConnect() {
        while (true) {
            int current = activeConnections.get();
            if (current >= MAX_CONNECTIONS) {
                System.out.printf("Превышен лимит соединений %d%n", MAX_CONNECTIONS);

                return false;
            }

            sleep(10);

            if (activeConnections.compareAndSet(current, current + 1)) {
                System.out.printf("Соединение установлено, активных соединений: %d%n", current + 1);

                return true;
            } else {
                System.out.printf("Не удалось выполнить соединение, выполняю повторную попытку, активных соединений: %d%n", current + 1);
            }
        }
    }
}
