package ru.panyukovnn.multithreadingmentoring.patterns.runparalleltasks;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.dto.Message;
import ru.panyukovnn.multithreadingmentoring.patterns.runparalleltasks.processor.MessageSender;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class SendInParallelExample {

    private final List<MessageSender> messageSenders;
    @Qualifier("runParallelTasksElasticExecutor")
    private final ExecutorService runParallelTasksElasticExecutor;

    public void sendMessageToSeveralTargets(Message message) {
        messageSenders.forEach(messageSender ->
            CompletableFuture.runAsync(() -> messageSender.send(message), runParallelTasksElasticExecutor)
        );
    }
}
