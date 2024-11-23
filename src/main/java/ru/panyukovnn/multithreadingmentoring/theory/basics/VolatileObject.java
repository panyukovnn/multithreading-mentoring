package ru.panyukovnn.multithreadingmentoring.theory.basics;

import java.util.List;

public class VolatileObject {

    private volatile List<String> whitelist = List.of("Bob", "Jack", "John");

    public boolean isInWhitelist(String username) {
        return whitelist.contains(username);
    }

    public void updateWhitelist(List<String> newWhitelist) {
        whitelist = newWhitelist;
    }
}
