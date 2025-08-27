package ru.panyukovnn.multithreadingmentoring.theory.basics;

import lombok.AllArgsConstructor;

import java.util.List;

public class VolatileObject {

    private volatile List<String> whitelist = List.of("Bob", "Jack", "John");

    public boolean isInWhitelist(String username) {
        return whitelist.contains(username);
    }

    public void updateWhitelist(List<String> newWhitelist) {
        whitelist = newWhitelist;
    }

    // ----------- Атомарное обновление значения двух координат ---------

    private volatile Coordinates coordinates = new Coordinates(0, 0);

    public void updateCoordinates(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    @AllArgsConstructor
    private static class Coordinates {

        private Integer x = 0;
        private Integer y = 0;
    }
}
