package ru.panyukovnn.multithreadingmentoring.patterns.ratelimiter;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.client.MarketPlaceParser;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final MarketPlaceParser marketPlaceParser;
    private final ExecutorService correctElasticExecutor;

    public String parsePage() {
        return marketPlaceParser.parsePage();
    }

    public CompletableFuture<String> parsePageWithRateLimiting() {
        try {
            return CompletableFuture.supplyAsync(marketPlaceParser::parsePage, correctElasticExecutor)
                .orTimeout(2, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e) {
            log.warn("Переполнена очередь выполонения задач");

            return CompletableFuture.completedFuture("");
        }
    }

    @RateLimiter(name = "marketPlaceParser", fallbackMethod = "fallbackMethod")
    public String parsePageWithRateLimitingResilience4j() {
        return marketPlaceParser.parsePage();
    }

    @Bulkhead(name = "marketPlaceParser", fallbackMethod = "fallbackMethod")
    public String parsePageWithBulkheadResilience4j() {
        return marketPlaceParser.parsePage();
    }

    protected String fallbackMethod(Exception ex) {
        log.warn("Превышено время ожидания на выполнение запроса");

        return "";
    }
}
