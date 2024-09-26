package ru.panyukovnn.multithreadingmentoring.patterns.awaitasyncresponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaRequest;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaResponse;
import ru.panyukovnn.multithreadingmentoring.util.JsonUtil;

/**
 * Заглушка, для имитации внешнего сервиса
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaOuterServiceStub {

    @Value("${app.kafka.topicOut}")
    private String topicOut;

    private final JsonUtil jsonUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "${app.kafka.topicIn}", groupId = "test")
    public void stub(ConsumerRecord<String, String> consumerRecord) {
        KafkaRequest kafkaRequest = jsonUtil.fromJson(consumerRecord.value(), KafkaRequest.class);

        KafkaResponse demoResponse = new KafkaResponse(kafkaRequest.getId(), "demo response");

        String rawResponse = jsonUtil.toJson(demoResponse);
        kafkaTemplate.send(topicOut, rawResponse);
    }
}
