package ru.panyukovnn.multithreadingmentoring.theory.immutable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DeepCopyUtil {

    public static <T> T deepCopy(T t, TypeReference<T> typeReference) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = objectMapper.writeValueAsBytes(t);

            return objectMapper.readValue(bytes, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка клонирования объекта: " + e.getMessage(), e);
        }
    }
}
