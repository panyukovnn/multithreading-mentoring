package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicExample {

    private final AtomicInteger counter = new AtomicInteger(100);

    public void atomic() {
        counter.get();

        counter.getAndIncrement();
        counter.incrementAndGet();

        counter.getAndDecrement();
        counter.decrementAndGet();

        counter.addAndGet(10);
    }

    // ---------------------------------

    private final AtomicReference<List<String>> schemas = new AtomicReference<>(new ArrayList<>());

    public void addSchema(String schema) {
        schemas.updateAndGet(list -> {
            if (!list.contains(schema)) {
                list.add(schema);
            }

            return list;
        });
    }
}
