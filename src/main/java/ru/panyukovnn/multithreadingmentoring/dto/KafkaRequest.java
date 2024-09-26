package ru.panyukovnn.multithreadingmentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaRequest {

    private String id;
    private String payload;
}
