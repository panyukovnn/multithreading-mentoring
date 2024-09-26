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
    public ExecutorService refreshCacheSingleExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService analyticsSingleExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService simpleSingleExecutor() {
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
        return createElasticExecutor(10, 1000);
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService productInfoElasticExecutor() {
        return createElasticExecutor(10, 1000);
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService feedbacksElasticExecutor() {
        return createElasticExecutor(10, 1000);
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService parserElasticExecutor() {
        return createElasticExecutor(10, 100);
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService runParallelTasksElasticExecutor() {
        return createElasticExecutor(10, 100);
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService runAsyncTasksElasticExecutor() {
        return createElasticExecutor(10, 100);
    }

    private ThreadPoolExecutor createElasticExecutor(int threads, int queueCapacity) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueCapacity);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            threads, threads,
            60L, TimeUnit.SECONDS,
            queue, new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        return threadPoolExecutor;
    }
}
