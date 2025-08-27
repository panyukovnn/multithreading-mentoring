package ru.panyukovnn.multithreadingmentoring.theory.concurrent.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static ru.panyukovnn.multithreadingmentoring.util.MentoringUtil.sleep;

@Slf4j
public class CompletableFutureExample {

    private final MessageRepository messageRepository = new MessageRepository();

    private final ExecutorService dbExecutor = Executors.newFixedThreadPool(10);
    private final ExecutorService clientOneExecutor = Executors.newFixedThreadPool(10);
    private final ExecutorService clientTwoExecutor = Executors.newFixedThreadPool(10);

    /**
     * Необходимо написать приложение-адаптер, которое получает сообщение и рассылает его во внешние сервисы
     * Должны быть реализованы следующие шаги:
     * - получает сообщение
     * - сохраняет в бд в статусе IN_PROCESS
     * - отправлять в 2 микросервиса
     * - сохраняет в бд в статусе DELIVERED
     * - если произошла ошибка, то сохраняет в бд статусе ERROR
     * <p>
     * При этом должен быть установлен таймаут
     */
    public CompletableFuture<Message> process(Message message) {
        return CompletableFuture.supplyAsync(() -> {
                    message.setState(MessageState.IN_PROCESS);

                    return messageRepository.save(message);
                },
                dbExecutor)
            .thenComposeAsync(msg ->
                    callSeveralCompletableFutures(msg)
                        .thenApplyAsync(responses -> {
                                msg.setResponses(responses);
                                messageRepository.save(msg);

                                return msg;
                            },
                            dbExecutor),
                dbExecutor)
            .orTimeout(5, TimeUnit.SECONDS)
            .thenApplyAsync(msg -> {
                    log.info("Сообщение успешно отправлено во внешние сервисы");

                    msg.setState(MessageState.DELIVERED);
                    messageRepository.save(msg);

                    return msg;
                },
                dbExecutor)
            .exceptionallyAsync(ex -> {
                log.warn("Произошла ошибка при рассылке сообщения", ex);

                message.setState(MessageState.ERROR);
                messageRepository.save(message);

                return message;
            });
    }

    private CompletableFuture<List<String>> callSeveralCompletableFutures(Message message) {
        CompletableFuture<String> requestOne = sendMsOneRequest(message);
        CompletableFuture<String> requestTwo = sendMsTwoRequest(message);

        return CompletableFuture.allOf(requestOne, requestTwo)
            .thenApply(ignore -> {
                String responseOne = requestOne.getNow("");
                String responseTwo = requestTwo.getNow("");

                return List.of(responseOne, responseTwo);
            });
    }

    private CompletableFuture<String> sendMsOneRequest(Message message) {
        return CompletableFuture.supplyAsync(() -> {
                // Вызов внешнего сервиса
                sleep(2000);

                return "Response 1";
            },
            clientOneExecutor);
    }

    private CompletableFuture<String> sendMsTwoRequest(Message message) {
        return CompletableFuture.supplyAsync(() -> {
                // Вызов внешнего сервиса
                sleep(1000);

                return "Response 2";
            },
            clientTwoExecutor);
    }
}
