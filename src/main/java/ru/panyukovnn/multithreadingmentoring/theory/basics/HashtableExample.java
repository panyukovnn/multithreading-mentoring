package ru.panyukovnn.multithreadingmentoring.theory.basics;

import java.util.Hashtable;

public class HashtableExample {

    private Hashtable<String, String> hashtable = new Hashtable<>();

    public void checkThenPut(String key) {
        if (!hashtable.containsKey(key)) {
            hashtable.put(key, "value");
        }
    }
}
