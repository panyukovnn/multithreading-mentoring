package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollectionsExample {

    public void throwsConcurrentModificationException() {
        List<Integer> ints = new ArrayList<>(List.of(1, 2, 3));

        Iterator<Integer> iterator = ints.iterator();

        ints.remove(0);

        if (iterator.hasNext()) {
            iterator.next();
        }

        System.out.println(ints);
    }

    public void noConcurrentModificationException() {
        List<Integer> ints = new CopyOnWriteArrayList<>(List.of(1, 2, 3));

        for (Integer i : ints) {
            ints.remove(0);
        }

        System.out.println(ints);
    }
}
