package ru.panyukovnn.multithreadingmentoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.panyukovnn.multithreadingmentoring.patterns.awaitasyncresponse.AwaitAsyncRequestService;
import ru.panyukovnn.multithreadingmentoring.patterns.context.caffeine.CaffeineExampleService;
import ru.panyukovnn.multithreadingmentoring.patterns.collectparallelrequests.ParallelRequestService;
import ru.panyukovnn.multithreadingmentoring.patterns.ratelimiter.RateLimiterService;
import ru.panyukovnn.multithreadingmentoring.patterns.runparalleltasks.SendInParallelExample;

@SpringBootTest
public class AbstractTest {

    @Autowired
    protected RateLimiterService rateLimiterService;

    @Autowired
    protected ParallelRequestService parallelRequestService;

    @Autowired
    protected AwaitAsyncRequestService awaitAsyncRequestService;

    @Autowired
    protected CaffeineExampleService caffeineExampleService;

    @Autowired
    protected SendInParallelExample sendInParallelExample;
}
