package ru.panyukovnn.multithreadingmentoring.patterns.awaitasyncresponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaRequest;
import ru.panyukovnn.multithreadingmentoring.util.JsonUtil;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${app.kafka.topicIn}")
    private String topicIn;

    private final JsonUtil jsonUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(KafkaRequest kafkaRequest) {
        String rawRequest = jsonUtil.toJson(kafkaRequest);

        kafkaTemplate.send(topicIn, rawRequest);
    }
}
