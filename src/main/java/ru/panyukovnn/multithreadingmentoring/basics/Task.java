package ru.panyukovnn.multithreadingmentoring.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("Запущено из наследника Thread");
    }
}