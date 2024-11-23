package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private static Integer counter = 0;

    public void incrementCounter() {
        reentrantLock.lock();

        try {
            counter = counter +1;
        } finally {
            reentrantLock.unlock();
        }
    }

    // ----------------- tryLock -----------------

    public void tryLockExample() {
        boolean locked = reentrantLock.tryLock();

        if (!locked) {
            return;
        }

        try {
            counter = counter +1;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void tryLockWithTimeoutExample() throws InterruptedException {
        boolean locked = reentrantLock.tryLock(5, TimeUnit.SECONDS);

        if (!locked) {
            return;
        }

        try {
            counter = counter +1;
        } finally {
            reentrantLock.unlock();
        }
    }
}
