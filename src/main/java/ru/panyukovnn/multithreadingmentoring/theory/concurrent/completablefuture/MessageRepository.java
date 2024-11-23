package ru.panyukovnn.multithreadingmentoring.theory.concurrent.completablefuture;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MessageRepository {

    private final Map<UUID, Message> storage = new ConcurrentHashMap<>();

    public Message save(Message message) {
        storage.put(message.getId(), message);

        return message;
    }
}
