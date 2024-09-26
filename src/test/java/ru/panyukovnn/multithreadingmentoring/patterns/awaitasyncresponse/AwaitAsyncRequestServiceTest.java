package ru.panyukovnn.multithreadingmentoring.patterns.awaitasyncresponse;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.panyukovnn.multithreadingmentoring.AbstractTest;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaRequest;
import ru.panyukovnn.multithreadingmentoring.dto.KafkaResponse;

import java.util.UUID;

@Slf4j
class AwaitAsyncRequestServiceTest extends AbstractTest {

    @Test
    void sendAndReceiveAsyncResponse() {
        KafkaRequest demoRequest = new KafkaRequest(UUID.randomUUID().toString(), "demo request");
        KafkaResponse response = awaitAsyncRequestService.sendAndReceive(demoRequest);

        log.info("Получен ответ от внешнего сервиса через kafka: {}", response);
    }
}