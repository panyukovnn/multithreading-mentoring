package ru.panyukovnn.multithreadingmentoring.patterns.runparalleltasks.processor;

import ru.panyukovnn.multithreadingmentoring.dto.Message;

public interface MessageSender {

    void send(Message message);
}
