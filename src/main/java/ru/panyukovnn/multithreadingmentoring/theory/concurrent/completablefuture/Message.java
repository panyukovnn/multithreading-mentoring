package ru.panyukovnn.multithreadingmentoring.theory.concurrent.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Message {

    private UUID id;
    private MessageState state;
    private List<String> responses;

}