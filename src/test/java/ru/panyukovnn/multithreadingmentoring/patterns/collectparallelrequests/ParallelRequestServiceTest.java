package ru.panyukovnn.multithreadingmentoring.patterns.collectparallelrequests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.panyukovnn.multithreadingmentoring.AbstractTest;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
class ParallelRequestServiceTest extends AbstractTest {

    /**
     * Для корректной работы надо поднять httpbin в docker, с помощью команды:
     * docker run -p 7778:80 --name httpbin kennethreitz/httpbin
     */

    @Test
    void sequentialExecution() {
        long startTime = System.currentTimeMillis();

        parallelRequestService.sequentialExecution(UUID.randomUUID());

        log.info("Время выполнения: {} мс", System.currentTimeMillis() - startTime);
    }

    @Test
    void parallelExecution() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        parallelRequestService.parallelExecution(UUID.randomUUID())
            .get();

        log.info("Время выполнения: {} мс", System.currentTimeMillis() - startTime);
    }
}