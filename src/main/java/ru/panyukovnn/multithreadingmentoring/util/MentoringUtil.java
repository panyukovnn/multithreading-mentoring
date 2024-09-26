package ru.panyukovnn.multithreadingmentoring.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class MentoringUtil {

    private final ExecutorService longOperationExecutor = Executors.newFixedThreadPool(10);

    @SneakyThrows
    public String executeLongOperation() {
        Thread.sleep(10000);

        return "Долгая операция выполнена успешно";
    }

    @Async("elasticExecutor")
    public CompletableFuture<String> executeSyncLongOperationInDifferentThreadPool() {
        String result = executeLongOperation();

        log.info(result);

        return CompletableFuture.completedFuture("Ваш запрос получен и будет обработан");
    }

    public String executeAsyncLongOperation() {
        CompletableFuture.runAsync(
            () -> {
                String result = executeLongOperation();

                log.info(result);
            },
            longOperationExecutor
        );

        return "Ваш запрос получен и будет обработан";
    }

    @SneakyThrows
    public static void sleep(int millis) {
        Thread.sleep(millis);
    }
}
