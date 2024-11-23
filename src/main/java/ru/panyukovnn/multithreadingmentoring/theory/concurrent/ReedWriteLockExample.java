package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReedWriteLockExample {

    private final Map<String, String> map = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public String put(String key, String value) {
        writeLock.lock();

        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        readLock.lock();

        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
}
