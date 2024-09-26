package ru.panyukovnn.multithreadingmentoring.patterns.runparalleltasks.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.dto.Message;
import ru.panyukovnn.multithreadingmentoring.util.MentoringUtil;

@Slf4j
@Service
public class MessageSender2 implements MessageSender {

    @Override
    public void send(Message message) {
        MentoringUtil.sleep(2000);

        log.info("Сообщение отправлено в источник #2");
    }
}
