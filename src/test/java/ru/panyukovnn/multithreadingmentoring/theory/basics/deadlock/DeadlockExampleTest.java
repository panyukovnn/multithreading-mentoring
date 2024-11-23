package ru.panyukovnn.multithreadingmentoring.theory.basics.deadlock;

import org.junit.jupiter.api.Test;

class DeadlockExampleTest {

    private final DeadlockExample deadlockExample = new DeadlockExample();

    @Test
    void showDeadlock() throws InterruptedException {
        Account account1 = new Account();
        Account account2 = new Account();

        Thread thread = new Thread(() -> {
            deadlockExample.transfer(account1, account2, 100);
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            deadlockExample.transfer(account2, account1, 50);
        });
        thread1.start();

        thread.join();

        System.out.println("Приложение никогда не завершится");
    }

}