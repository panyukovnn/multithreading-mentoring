package ru.panyukovnn.multithreadingmentoring.theory.basics;

import java.util.Hashtable;

public class HashtableExample {

    private Hashtable<String, String> hashtable = new Hashtable<>();

    public void checkThenPut(String key, String value) {
        if (!hashtable.containsKey(key)) {
            // какие-то операции

            hashtable.put(key, value);
        }
    }
}
