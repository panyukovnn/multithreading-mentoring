package ru.panyukovnn.multithreadingmentoring.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadsDaemon {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                log.info("Вызван блок finally");
            }

            log.info("Запущено в потоке-демоне");
        });

        thread.setDaemon(true);

        thread.start(); // Если не дожидаться, то он аварийно завершится до того, как выведет сообщение в лог

        Thread.sleep(100);

        thread.join();
    }
}
