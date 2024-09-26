package ru.panyukovnn.multithreadingmentoring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public <T> String toJson(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Ошибка преобразования объекта в строку: {}", e.getMessage(), e);

            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String value, Class<T> clazz) {
        try {
            return objectMapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            log.error("Ошибка парсинга json: {}", e.getMessage(), e);

            throw new RuntimeException(e);
        }
    }

    public <T> T fromJsonWithTypeReferehce(String value) {
        try {
            return objectMapper.readValue(value, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Ошибка парсинга json: {}", e.getMessage(), e);

            throw new RuntimeException(e);
        }
    }
}
