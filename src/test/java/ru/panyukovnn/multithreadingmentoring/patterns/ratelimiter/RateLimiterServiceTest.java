package ru.panyukovnn.multithreadingmentoring.patterns.ratelimiter;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
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
    void parsePageWithRateLimiter() {
        callInParallel(100, () -> {
            try {
                rateLimiterService.parsePageWithRateLimiting().get();
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
    void parsePageWithRateLimiterResilience4jBulkhead() {
        callInParallel(2000, () -> rateLimiterService.parsePageWithBulkheadResilience4j());
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