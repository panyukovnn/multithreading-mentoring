package ru.panyukovnn.multithreadingmentoring.theory.basics.deadlock;

public class Account {

    long amount = 1000;

    public void increment(long amount) {
        this.amount += amount;
    }

    public void decrement(long amount) {
        this.amount -= amount;
    }
}
