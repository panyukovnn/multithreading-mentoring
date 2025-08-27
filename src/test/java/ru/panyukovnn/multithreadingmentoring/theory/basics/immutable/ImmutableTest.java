package ru.panyukovnn.multithreadingmentoring.theory.basics.immutable;

import org.junit.jupiter.api.Test;
import ru.panyukovnn.multithreadingmentoring.theory.immutable.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ImmutableTest {

    // -------------- mutable --------------

    private Address address = new Address("Санкт-Петербург", "Невский проспект", "1");
    private List<Role> roles = new ArrayList<>();
    private List<String> comments = new ArrayList<>();

    private Person person = new Person("Леон", address, roles, comments);
    private ImmutablePerson immutablePerson = new ImmutablePerson("Леон", address, roles, comments);

    @Test
    void when_changeFirstnameForMutableObject_then_differentHashCode() {
        int hashCodeBefore = person.hashCode();
        person.setFirstname("Leon");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeAddressForMutableObject_then_differentHashCode() {
        int hashCodeBefore = person.hashCode();
        person.getAddress().setCity("Москва");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeAddressOutsideForMutableObject_then_differentHashCode() {
        Address address = new Address("Санкт-Петербург", "Невский проспект", "1");
        Person person = new Person("Леон", address, roles, comments);

        int hashCodeBefore = person.hashCode();
        address.setCity("Москва");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeRolesForMutableObject_then_differentHashCode() {
        int hashCodeBefore = person.hashCode();
        person.getRoles().add(new Role("EDITOR"));
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeOneOfRoleForMutableObject_then_differentHashCode() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("EDITOR"));
        Person person = new Person("Леон", address, roles, comments);

        int hashCodeBefore = person.hashCode();
        person.getRoles().get(0).setName("READER");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeCommentsForMutableObject_then_differentHashCode() {
        int hashCodeBefore = person.hashCode();
        person.getComments().add("Случайный комментарий");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_createMutableChildClass_then_differentHashCode() {
        Person person = new PersonChild("Леон", "Кеннеди", address, roles, comments);

        int hashCodeBefore = person.hashCode();
        ((PersonChild) person).setLastname("Leon");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    // -------------- immutable --------------

    @Test
    void when_changeCityForImmutableObject_then_sameHashCode() {
        int hashCodeBefore = immutablePerson.hashCode();
        immutablePerson.getAddress().setCity("Москва");
        int hashCodeAfter = immutablePerson.hashCode();

        assertEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeCityViaConstructorForImmutableObject_then_sameHashCode() {
        Address address = new Address("Санкт-Петербург", "Невский проспект", "1");
        ImmutablePerson immutablePerson = new ImmutablePerson("Леон", address, roles, comments);

        int hashCodeBefore = immutablePerson.hashCode();
        address.setCity("Москва");
        int hashCodeAfter = immutablePerson.hashCode();

        assertEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeRolesForImmutableObject_then_sameHashCode() {
        int hashCodeBefore = immutablePerson.hashCode();
        immutablePerson.getRoles().add(new Role("EDITOR"));
        int hashCodeAfter = immutablePerson.hashCode();

        assertEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeOneOfRolesForImmutableObject_then_sameHashCode() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("EDITOR"));
        ImmutablePerson immutablePerson = new ImmutablePerson("Леон", address, roles, comments);

        int hashCodeBefore = immutablePerson.hashCode();
        immutablePerson.getRoles().get(0).setName("READER");
        int hashCodeAfter = immutablePerson.hashCode();

        assertEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeCommentsForImmutableObject_then_differentHashCode() {
        int hashCodeBefore = immutablePerson.hashCode();
        immutablePerson.getComments().add("Случайный комментарий");
        int hashCodeAfter = immutablePerson.hashCode();

        assertEquals(hashCodeBefore, hashCodeAfter);
    }
}
