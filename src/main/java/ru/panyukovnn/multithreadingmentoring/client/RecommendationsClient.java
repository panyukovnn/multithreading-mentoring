package ru.panyukovnn.multithreadingmentoring.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panyukovnn.multithreadingmentoring.dto.Recommendation;
import ru.panyukovnn.multithreadingmentoring.util.JsonUtil;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationsClient {

    public static final String base64Recommendations = "Wwp7CiAgICAicmV2aWV3UmF0aW5nIjogIjUiLAogICAgInRleHQiOiAi0J7RgtC70LjRh9C90YvQuSIKfSwKewogICAgInJldmlld1JhdGluZyI6ICIxIiwKICAgICJ0ZXh0IjogItCf0YDQvtC00LDQstC10YYg0LTQvtC70LPQviDQtNC+0YHRgtCw0LLQu9GP0LsiCn0sCnsKICAgICJyZXZpZXdSYXRpbmciOiAiNCIsCiAgICAidGV4dCI6ICLQn9GA0LjRiNC10Lsg0YEg0YbQsNGA0LDQv9C40L3QvtC5Igp9Cl0=";

    private final JsonUtil jsonUtil;
    private final RestTemplate restTemplate;

    public List<Recommendation> fetchRecommendations() {
        restTemplate.getForObject("https://httpbin.org/delay/3", String.class);

        log.info("Выполнен запрос с задержкой Recommendations");

        String rawResponse = restTemplate.getForObject("https://httpbin.org/base64/" + base64Recommendations + "=", String.class);

        List<Recommendation> recommendations = jsonUtil.fromJsonWithTypeReferehce(rawResponse);

        log.info("Рекомендации получены: {}", recommendations);

        return recommendations;
    }
}
