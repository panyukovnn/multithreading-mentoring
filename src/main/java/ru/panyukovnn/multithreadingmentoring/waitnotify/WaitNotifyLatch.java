package ru.panyukovnn.multithreadingmentoring.waitnotify;

public class WaitNotifyLatch {

    private final Object lock = new Object();
    private volatile boolean release = false;

    public void await() throws InterruptedException {
        if (release) {
            throw new IllegalStateException("WaitNotifyLatch can't be reused");
        }

        synchronized (lock) {
            while (!release) {
                lock.wait();
            }
        }
    }

    public void release() {
        synchronized (lock) {
            release = true;
            lock.notifyAll();
        }
    }
}
