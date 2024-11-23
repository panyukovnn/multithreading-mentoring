package ru.panyukovnn.multithreadingmentoring.theory.immutable;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Person {

    private int age;
    private City city;
    private List<String> roles;

    public Person(int age, City city, List<String> roles) {
        this.age = age;
        this.city = city;
        this.roles = roles;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return age == that.age && Objects.equals(city, that.city) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, city, roles);
    }
}
