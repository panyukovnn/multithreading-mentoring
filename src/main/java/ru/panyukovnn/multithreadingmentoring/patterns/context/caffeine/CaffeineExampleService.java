package ru.panyukovnn.multithreadingmentoring.patterns.context.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.util.MentoringUtil;

import java.time.Duration;

@Slf4j
@Service
public class CaffeineExampleService {

    private final Cache<String, String> cache = Caffeine.newBuilder()
        .expireAfterAccess(Duration.ofMinutes(1))
        .maximumSize(100)
        .build();

    public String process(String request) {
        String cachedResult = cache.getIfPresent(request);
        if (cachedResult != null) {
            log.info("Возвращаю закешированный результат");

            return cachedResult;
        }

        MentoringUtil.sleep(1000);

        String response = "test response";
        cache.put(request, response);

        return response;
    }
}
