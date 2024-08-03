package ru.panyukovnn.multithreadingmentoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@Configuration
@EnableAsync
@EnableScheduling
public class MentoringConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService simpleSingleExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService simpleSingleExecutor2() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService correctSingleExecutor() {
        return new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService cachedExecutor() {
        return Executors.newWorkStealingPool();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService correctElasticExecutor() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10, 10,
            60L, TimeUnit.SECONDS,
            queue, new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        return threadPoolExecutor;
    }
}
