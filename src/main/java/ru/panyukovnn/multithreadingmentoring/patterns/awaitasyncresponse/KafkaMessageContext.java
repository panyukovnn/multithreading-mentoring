package ru.panyukovnn.multithreadingmentoring.patterns.awaitasyncresponse;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaResponse;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessageContext {

    private final Cache<String, CompletableFuture<KafkaResponse>> messageContext = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(1))
        .build();

    public CompletableFuture<KafkaResponse> createMessageCompletableFuture(String id) {
        CompletableFuture<KafkaResponse> completableFuture = new CompletableFuture<>();
        messageContext.put(id, completableFuture);

        return completableFuture;
    }

    public CompletableFuture<KafkaResponse> findById(String id) {
        return messageContext.getIfPresent(id);
    }

    public void removeById(String id) {
        messageContext.invalidate(id);
    }
}
