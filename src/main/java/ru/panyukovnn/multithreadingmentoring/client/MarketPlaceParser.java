package ru.panyukovnn.multithreadingmentoring.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketPlaceParser {

    @Value("${app.httpbin-url}")
    private String httpbinUrl;

    private final RestTemplate restTemplate;

    public String parsePage() {
        String response = restTemplate.getForObject(httpbinUrl + "/delay/2", String.class);

        log.info("Данные извлечены");

        return response;
    }
}
