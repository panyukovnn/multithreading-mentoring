package ru.panyukovnn.multithreadingmentoring.threadscost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.panyukovnn.multithreadingmentoring.util.MentoringUtil.sleep;

@Slf4j
@Service
public class CreateMaxThreads {

    /**
     * macbook pro m1 ограничение примерно 4072 системных потока
     */
//    @PostConstruct
    public void tryToCreateMaxThreads() {
        int threadsNumber = 3965;

        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);

        for (int i = 0; i < threadsNumber; i++) {
            executor.submit(() -> sleep(10000));
        }
    }
}
