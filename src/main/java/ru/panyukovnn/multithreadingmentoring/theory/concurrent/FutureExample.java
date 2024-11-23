package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample {

    public void demoFuture() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        log.info("start");

        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);

            return "finish";
        });

        Thread.sleep(1000);

        String result = future.get();

        log.info(result);

        executorService.shutdown();
    }
}
