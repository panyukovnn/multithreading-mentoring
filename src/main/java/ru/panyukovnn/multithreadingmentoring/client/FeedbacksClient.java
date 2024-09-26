package ru.panyukovnn.multithreadingmentoring.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.panyukovnn.multithreadingmentoring.dto.Feedback;
import ru.panyukovnn.multithreadingmentoring.util.JsonUtil;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedbacksClient {

    public static final String base64Feedbacks = "Wwp7CiAgICAicmV2aWV3UmF0aW5nIjogIjUiLAogICAgInRleHQiOiAi0J7RgtC70LjRh9C90YvQuSIKfSwKewogICAgInJldmlld1JhdGluZyI6ICIxIiwKICAgICJ0ZXh0IjogItCf0YDQvtC00LDQstC10YYg0LTQvtC70LPQviDQtNC+0YHRgtCw0LLQu9GP0LsiCn0sCnsKICAgICJyZXZpZXdSYXRpbmciOiAiNCIsCiAgICAidGV4dCI6ICLQn9GA0LjRiNC10Lsg0YEg0YbQsNGA0LDQv9C40L3QvtC5Igp9Cl0=";

    private final JsonUtil jsonUtil;
    private final RestTemplate restTemplate;

    public List<Feedback> fetchFeedbacks(UUID productId) {
        restTemplate.getForObject("https://httpbin.org/delay/3", String.class);

        log.info("Выполнен запрос с задержкой Feedbacks");

        String rawResponse = restTemplate.getForObject("https://httpbin.org/base64/" + base64Feedbacks + "=", String.class);

        List<Feedback> feedbacks = jsonUtil.fromJsonWithTypeReferehce(rawResponse);

        log.info("Отзывы получены: {}", feedbacks);

        return feedbacks;
    }
}
