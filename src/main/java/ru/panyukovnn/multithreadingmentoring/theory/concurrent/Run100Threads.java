package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Run100Threads {

    private ExecutorService correctElasticExecutor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {
        Run100Threads run100Threads = new Run100Threads();
        run100Threads.run100TasksSimultaneously();

        run100Threads.correctElasticExecutor.shutdown();
    }

    public void run100TasksSimultaneously() {
        int threadCount = 100;

        CountDownLatch latch = new CountDownLatch(threadCount + 1);

        for (int i = 0; i < threadCount; i++) {
            correctElasticExecutor.execute(() -> {
                log.info("countDown and await");

                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.info("finish");
            });
        }

        log.info("last countDown, open barrier");

        latch.countDown();

        log.info("after last countDown");
    }
}
