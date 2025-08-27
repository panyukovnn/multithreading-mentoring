package ru.panyukovnn.multithreadingmentoring.theory.immutable;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.panyukovnn.multithreadingmentoring.theory.immutable.DeepCopyUtil.deepCopy;

public final class ImmutablePerson {

    private final String firstname;
    private final Address address;
    private final List<Role> roles;
    private final List<String> comments;

    public ImmutablePerson(String firstname, Address address, List<Role> roles, List<String> comments) {
        this.firstname = firstname;
        this.address = deepCopy(address, new TypeReference<>() {
        });
        this.roles = deepCopy(roles, new TypeReference<>() {
        });
        this.comments = List.copyOf(comments);
    }

    public String getFirstname() {
        return firstname;
    }

    public Address getAddress() {
        return deepCopy(this.address, new TypeReference<>() {
        });
    }

    public List<Role> getRoles() {
        return deepCopy(this.roles, new TypeReference<>() {
        });
    }

    public List<String> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutablePerson that = (ImmutablePerson) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(address, that.address) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, address, roles);
    }
}
