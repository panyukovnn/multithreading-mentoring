package ru.panyukovnn.multithreadingmentoring.theory.basics;

import java.util.Hashtable;

public class HashtableSynchronizedExample {

    private Hashtable<String, String> hashtable = new Hashtable<>();

    public void checkThenPut(String key) {
        synchronized (hashtable) {
            if (!hashtable.containsKey(key)) {
                hashtable.put(key, "value");
            }
        }
    }
}
