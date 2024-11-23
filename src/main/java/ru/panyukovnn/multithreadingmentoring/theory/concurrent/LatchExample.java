package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

import static ru.panyukovnn.multithreadingmentoring.util.MentoringUtil.sleep;

@Slf4j
@Component
public class LatchExample {

    public void demoLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            startNewThread(countDownLatch);
        }

        countDownLatch.await();

        log.info("Дождались выполнения всех потоков");
    }

    private static void startNewThread(CountDownLatch countDownLatch) {
        new Thread(() -> {
            sleep(1000);

            log.info("Поток закончил работу: " + Thread.currentThread());

            countDownLatch.countDown();
        }).start();
    }
}
