package ru.panyukovnn.multithreadingmentoring.patterns.ratelimiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.panyukovnn.multithreadingmentoring.AbstractTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
class RateLimiterServiceTest extends AbstractTest {

    @Test
    void parsePageNoLimits() {
        callInParallel(2000, rateLimiterService::parsePage);
    }

    @Test
    void parsePageWithSemaphoreRateLimiting() {
        callInParallel(100, rateLimiterService::parsePageWithSemaphoreRateLimiting);
    }

    @Test
    void parsePageWithRateLimiter() {
        callInParallel(1100, () -> {
            try {
                rateLimiterService.parsePageWithRateLimitingCompletableFuture()
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.warn(ex.getMessage(), ex);
                        } else {
                            log.info(result);
                        }
                    })
                    .get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void parsePageWithRateLimiterResilience4j() {
        callInParallel(2000, () -> rateLimiterService.parsePageWithRateLimitingResilience4j());
    }

    @Test
    void parsePageWithRateLimiterResilience4jCompletableFuture() {
        callInParallel(150, () -> {
            try {
                rateLimiterService.parsePageWithRateLimitingResilience4jCompletableFuture().get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void parsePageWithRateLimiterResilience4jBulkheadSemaphore() {
        callInParallel(150, rateLimiterService::parsePageWithSemaphoreBulkheadResilience4j);
    }

    @Test
    void parsePageWithRateLimiterResilience4jBulkheadThreadpoolAndTimeLimiter() {
        callInParallel(150, () -> {
            try {
                rateLimiterService.parsePageWithThreadPoolBulkheadAndTimeLimiterResilience4j().get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void callInParallel(int threadsNumber, Runnable runnable) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadsNumber; i++) {
            Thread thread = new Thread(runnable);
            thread.start();

            threads.add(thread);
        }

        threads.forEach(it -> {
            try {
                it.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}