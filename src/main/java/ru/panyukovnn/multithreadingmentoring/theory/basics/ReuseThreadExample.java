package ru.panyukovnn.multithreadingmentoring.theory.basics;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Демонстрация переиспользования потоков в пулах
 */
@Slf4j
public class ReuseThreadExample {

    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Runnable runnable = queue.take();

                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })
            .start();

        queue.add(() -> System.out.println("Моя задача"));
    }
}
