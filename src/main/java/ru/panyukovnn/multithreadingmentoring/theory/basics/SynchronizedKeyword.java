package ru.panyukovnn.multithreadingmentoring.theory.basics;

public class SynchronizedKeyword {

    private final Object lock = new Object();
    private static Integer counter = 0;

    public void incrementCounter() {
        synchronized (lock) {
            // Критическая секция
            counter = counter + 1;
        }
    }

    public void decrementCounter() {
        synchronized (lock) {
            counter = counter - 1;
        }
    }

    public synchronized void decrementCounterMethodSignature() {
        counter--;
    }

    public synchronized static void decrementCounterStaticSignature() {
//        synchronized (SynchronizedKeyword.class) {
            counter--;
//        }
    }
}
