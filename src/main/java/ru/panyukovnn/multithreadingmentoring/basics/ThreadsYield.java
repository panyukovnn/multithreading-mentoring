package ru.panyukovnn.multithreadingmentoring.basics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadsYield {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            // Тяжелая операция

            // Если у нас 1 ядро, то мы займем все время выполнения своими тяжелыми операциями
            // Чтобы другие потоки в приложении не простаивали, мы можем уступить процессорное время
            // Данная инструкция является лишь подсказкой для JVM и может быть проигнорирована
            Thread.yield();
        }
    }
}
