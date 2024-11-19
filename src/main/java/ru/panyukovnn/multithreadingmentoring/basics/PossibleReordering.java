package ru.panyukovnn.multithreadingmentoring.basics;

import lombok.SneakyThrows;

/**
 * Пример из книги Java concurrency in practice
 */
public class PossibleReordering {

    private int x = 0;
    private int y = 0;
    private int a = 0;
    private int b = 0;

    /**
     * В некоторых случаях результат даже равен 0, 0
     */
    @SneakyThrows
    public Pair tryReorderingOperations() {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });
        
        Thread other = new Thread(() -> {
            b = 1;
            y = a;
        });
        
        one.start();
        other.start();
        one.join();
        other.join();

        return new Pair(x, y);
	}

    public record Pair(Integer left, Integer right) {}
}