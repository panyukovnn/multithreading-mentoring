package ru.panyukovnn.multithreadingmentoring.theory.basics;

public class RaceCondition {

    // ------------------ check-then-act ------------------

    private RaceCondition instance = null;

    public RaceCondition getInstance() {
        if (instance == null) {
            instance = new RaceCondition();
        }

        return instance;
    }

    // ------------------ read-modify-write ------------------

    private int counter = 0;

    public void incrementCounter() {
        counter = counter + 1;
    }
}
