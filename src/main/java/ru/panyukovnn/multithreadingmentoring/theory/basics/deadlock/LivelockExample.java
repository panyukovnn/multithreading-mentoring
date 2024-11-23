package ru.panyukovnn.multithreadingmentoring.theory.basics.deadlock;

public class LivelockExample {

    public void liveLock() {
        while (true) {
            // Поток не заблокирован, но никогда не завершится
        }
    }
}
