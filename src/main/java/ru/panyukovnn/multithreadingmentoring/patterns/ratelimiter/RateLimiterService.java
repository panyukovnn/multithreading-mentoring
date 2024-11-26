package ru.panyukovnn.multithreadingmentoring.patterns.ratelimiter;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.client.MarketPlaceParser;
import ru.panyukovnn.multithreadingmentoring.exception.CustomAppException;

import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final MarketPlaceParser marketPlaceParser;
    private final ExecutorService parserElasticExecutor;

    // ------------------ No Limit ------------------

    public String parsePage() {
        return marketPlaceParser.parsePage();
    }

    // ------------------ Semaphore ------------------

    private final Semaphore semaphore = new Semaphore(10);

    public String parsePageWithSemaphoreRateLimiting() {
        boolean isAcquired;
        try {
            isAcquired = semaphore.tryAcquire(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new CustomAppException(e);
        }

        if (!isAcquired) {
            log.info("Не удалось захватить поток семафора");

            return "fallback result";
        }

        try {
            return marketPlaceParser.parsePage();
        } catch (Exception e) {
            return "fallback result";
        } finally {
            semaphore.release();
        }
    }

    // ------------------ CompletableFuture ------------------

    public CompletableFuture<String> parsePageWithRateLimitingCompletableFuture() {
        try {
            return CompletableFuture.supplyAsync(marketPlaceParser::parsePage, parserElasticExecutor)
                .orTimeout(3, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    log.warn("Ошибка при вызове внешнего сервиса", e);

                    return "fallback result";
                });
        } catch (RejectedExecutionException e) {
            log.warn("Переполнена очередь выполонения задач на вызов внешнего сервиса");

            return CompletableFuture.completedFuture("fallback result");
        }
    }

    // ------------------ Bulkhead ------------------

    @Bulkhead(name = "parserSemaphore", fallbackMethod = "fallbackMethod")
    public String parsePageWithSemaphoreBulkheadResilience4j() {
        return marketPlaceParser.parsePage();
    }

    @TimeLimiter(name = "parserThreadPool", fallbackMethod = "fallbackMethodCompletableFuture")
    @Bulkhead(name = "parserThreadPool", fallbackMethod = "fallbackMethodCompletableFuture", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> parsePageWithThreadPoolBulkheadAndTimeLimiterResilience4j() {
        String response = marketPlaceParser.parsePage();

        return CompletableFuture.completedFuture(response);
    }

    // ------------------ RateLimiter ------------------

    @RateLimiter(name = "parser", fallbackMethod = "fallbackMethod")
    public String parsePageWithRateLimitingResilience4j() {
        return marketPlaceParser.parsePage();
    }

    @RateLimiter(name = "parser", fallbackMethod = "fallbackMethodCompletableFuture")
    public CompletableFuture<String> parsePageWithRateLimitingResilience4jCompletableFuture() {
        return CompletableFuture.supplyAsync(marketPlaceParser::parsePage, parserElasticExecutor)
            .orTimeout(3, TimeUnit.SECONDS);
    }

    public String fallbackMethod(Exception e) {
        log.warn("Ошибка при вызове внешнего сервиса: {}", e.getMessage());

        return "fallback result";
    }

    public CompletableFuture<String> fallbackMethodCompletableFuture(Exception e) {
        log.warn("Ошибка при вызове внешнего сервиса: {}", e.getMessage());

        return CompletableFuture.completedFuture("fallback result");
    }
}
