package ru.panyukovnn.multithreadingmentoring.patterns.awaitasyncresponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaRequest;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaResponse;
import ru.panyukovnn.multithreadingmentoring.exception.CustomAppException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class AwaitAsyncRequestService {

    private final KafkaProducer kafkaProducer;
    private final KafkaMessageContext kafkaMessageContext;

    public KafkaResponse sendAndReceive(KafkaRequest request) {
        CompletableFuture<KafkaResponse> responseCompletableFuture = kafkaMessageContext.createMessageCompletableFuture(request.getId());

        kafkaProducer.send(request);

        try {
            return responseCompletableFuture
                .get(5000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | ExecutionException | InterruptedException e) {
            throw new CustomAppException(e);
        } finally {
            kafkaMessageContext.removeById(request.getId());
        }
    }
}
