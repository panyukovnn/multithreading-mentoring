package ru.panyukovnn.multithreadingmentoring.theory.immutable;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class ImmutablePerson {

    private final int age;
    private final City city;
    private final List<String> roles;

    public ImmutablePerson(int age, City city, List<String> roles) {
        this.age = age;
        this.city = cloneCity(city);
        this.roles = Collections.unmodifiableList(roles);
    }

    public int getAge() {
        return age;
    }

    @SneakyThrows
    public City getCity() {
        return cloneCity(this.city);
    }

    public List<String> getRoles() {
        return new ArrayList<>(roles);
    }

    @SneakyThrows
    private City cloneCity(City city) {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(city);

        return objectMapper.readValue(bytes, City.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutablePerson that = (ImmutablePerson) o;
        return age == that.age && Objects.equals(city, that.city) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, city, roles);
    }
}
