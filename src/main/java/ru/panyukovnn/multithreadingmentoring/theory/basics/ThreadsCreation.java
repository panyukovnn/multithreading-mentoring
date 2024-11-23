package ru.panyukovnn.multithreadingmentoring.theory.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadsCreation {

    public static void main(String[] args) {
        new Thread(() -> log.info("Запущено из new Thread"))
            .start();

        Task task = new Task();
        task.start();
//        task.start(); // Нельзя 2 раза запустить один поток

        Task task2 = new Task();
        task2.start();

        log.info("Запуск потоков выполнен");
    }
}
