package ru.panyukovnn.multithreadingmentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaRequest {

    private String id;
    private String payload;
}
