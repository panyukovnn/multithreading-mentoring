package ru.panyukovnn.multithreadingmentoring.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketPlaceParser {

    private final RestTemplate restTemplate;

    public String parsePage() {
        String response = restTemplate.getForObject("https://httpbin.org/delay/1", String.class);

        log.info("Данные извлечены");

        return response;
    }
}
