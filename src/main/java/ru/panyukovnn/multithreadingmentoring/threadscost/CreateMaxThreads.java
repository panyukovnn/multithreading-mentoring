package ru.panyukovnn.multithreadingmentoring.threadscost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class CreateMaxThreads {

//    @PostConstruct
    public void tryToCreateMaxThreads() {
        int threadsNumber = 4000;

        for (int i = 0; i < threadsNumber; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        log.info("test");

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}
