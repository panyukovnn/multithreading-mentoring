package ru.panyukovnn.multithreadingmentoring.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadsJoin {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        task.start();

        log.info("Перед join");

        task.join(); // Дожидаемся выполнения потока task, блокируя текущий поток

        log.info("После join");
    }
}
