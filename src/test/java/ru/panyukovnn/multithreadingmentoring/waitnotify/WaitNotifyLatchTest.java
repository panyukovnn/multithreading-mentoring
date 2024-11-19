package ru.panyukovnn.multithreadingmentoring.waitnotify;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

@Slf4j
class WaitNotifyLatchTest {

    private WaitNotifyLatch latch = new WaitNotifyLatch();

    @Test
    void latchMultiThreads_success() {
        int numberOfLatchedThreads = 10;

        Stream.generate(() -> new Thread(() -> {
                System.out.println("I'm locked");

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("I'm free");
            }))
            .limit(numberOfLatchedThreads)
            .forEach(Thread::start);

        System.out.println("Threads are created");

        new Thread(() -> {
            System.out.println("Before Latch release");

            sleep(1000);
            latch.release();

            System.out.println("After Latch release");
        }).start();

        sleep(5000);
    }

    @SneakyThrows
    void sleep(long millis) {
        Thread.sleep(millis);
    }
}