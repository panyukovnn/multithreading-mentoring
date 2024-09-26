package ru.panyukovnn.multithreadingmentoring.patterns.awaitasyncresponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaResponse;
import ru.panyukovnn.multithreadingmentoring.util.JsonUtil;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final JsonUtil jsonUtil;
    private final KafkaMessageContext kafkaMessageContext;

    @KafkaListener(topics = "${app.kafka.topicOut}", groupId = "test")
    public void receiveResponse(ConsumerRecord<String, String> consumerRecord) {
        KafkaResponse response = jsonUtil.fromJson(consumerRecord.value(), KafkaResponse.class);

        CompletableFuture<KafkaResponse> responseCompletableFuture = kafkaMessageContext.findById(response.getId());

        if (responseCompletableFuture == null) {
            log.info("Получено сообщение в ответный топик для которого не найден callback: {}", response);
        } else {
            responseCompletableFuture.complete(response);
        }
    }
}
