package ru.panyukovnn.multithreadingmentoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.panyukovnn.multithreadingmentoring.patterns.parallelrequeset.ParallelRequestService;
import ru.panyukovnn.multithreadingmentoring.patterns.ratelimiter.RateLimiterService;

@SpringBootTest
public class AbstractTest {

    @Autowired
    protected RateLimiterService rateLimiterService;

    @Autowired
    protected ParallelRequestService parallelRequestService;
}
