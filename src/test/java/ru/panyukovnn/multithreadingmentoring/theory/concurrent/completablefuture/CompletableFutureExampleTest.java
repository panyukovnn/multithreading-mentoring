package ru.panyukovnn.multithreadingmentoring.theory.concurrent.completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
class CompletableFutureExampleTest {

    private final CompletableFutureExample completableFutureExample = new CompletableFutureExample();

    @Test
    void process() throws ExecutionException, InterruptedException {
        Message message = new Message(UUID.randomUUID(), MessageState.NEW, new ArrayList<>());

        completableFutureExample.process(message)
            .whenComplete((res, ex) -> {
                if (ex != null) {
                    log.error(ex.getMessage(), ex);
                } else {
                    log.info(res.toString());
                }
            })
            .get();
    }
}