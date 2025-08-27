package ru.panyukovnn.multithreadingmentoring.theory.immutable;

import java.util.List;
import java.util.Objects;

public class Person {

    private String firstname;
    private Address address;
    private List<Role> roles;
    private List<String> comments;

    public Person(String firstname, Address address, List<Role> roles, List<String> comments) {
        this.firstname = firstname;
        this.address = address;
        this.roles = roles;
        this.comments = comments;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstname, person.firstname) && Objects.equals(address, person.address) && Objects.equals(roles, person.roles) && Objects.equals(comments, person.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, address, roles, comments);
    }
}
