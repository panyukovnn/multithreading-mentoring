package ru.panyukovnn.multithreadingmentoring.basics.immutable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class City {

    private String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(this.name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
