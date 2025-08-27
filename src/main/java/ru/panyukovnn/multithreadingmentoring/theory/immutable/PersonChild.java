package ru.panyukovnn.multithreadingmentoring.theory.immutable;

import java.util.List;
import java.util.Objects;

public class PersonChild extends Person {

    private String lastname;

    public PersonChild(String firstname, String lastname, Address address, List<Role> roles, List<String> comments) {
        super(firstname, address, roles, comments);
        this.lastname = lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonChild personChild = (PersonChild) o;
        return Objects.equals(getFirstname(), personChild.getFirstname()) && Objects.equals(getAddress(), personChild.getAddress()) && Objects.equals(getRoles(), personChild.getRoles()) && Objects.equals(getComments(), personChild.getComments()) && Objects.equals(lastname, personChild.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getAddress(), getRoles(), getComments(), lastname);
    }
}
