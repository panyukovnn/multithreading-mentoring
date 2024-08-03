package ru.panyukovnn.multithreadingmentoring.patterns.parallelrequeset;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.panyukovnn.multithreadingmentoring.AbstractTest;

import java.util.concurrent.ExecutionException;

@Slf4j
class ParallelRequestServiceTest extends AbstractTest {

    @Test
    void sequentialExecution() {
        long startTime = System.currentTimeMillis();

        parallelRequestService.sequentialExecution();

        log.info("Время выполнения: {}", System.currentTimeMillis() - startTime);
    }

    @Test
    void parallelExecution() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        parallelRequestService.parallelExecution()
            .get();

        log.info("Время выполнения: {}", System.currentTimeMillis() - startTime);
    }
}