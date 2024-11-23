package ru.panyukovnn.multithreadingmentoring.theory.basics.immutable;

import org.junit.jupiter.api.Test;
import ru.panyukovnn.multithreadingmentoring.theory.immutable.City;
import ru.panyukovnn.multithreadingmentoring.theory.immutable.ImmutablePerson;
import ru.panyukovnn.multithreadingmentoring.theory.immutable.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ImmutableTest {

    private final City city = new City("Saint-Petersburg");
    private final List<String> roles = new ArrayList<>();
    private final Person person = new Person(18, city, roles);
    private final ImmutablePerson immutablePerson = new ImmutablePerson(18, city, roles);

    // -------------- mutable --------------

    @Test
    void when_changeAgeForMutable_then_differentHashCode() {
        int hashCodeBefore = person.hashCode();
        person.setAge(19);
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeCityForMutable_then_differentHashCode() {
        int hashCodeBefore = person.hashCode();
        person.getCity().setName("Moscow");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeRolesForMutable_then_differentHashCode() {
        int hashCodeBefore = person.hashCode();
        person.getRoles().add("EDITOR");
        int hashCodeAfter = person.hashCode();

        assertNotEquals(hashCodeBefore, hashCodeAfter);
    }

    // -------------- immutable --------------

    @Test
    void when_changeCityForImmutable_then_sameHashCode() {
        int hashCodeBefore = immutablePerson.hashCode();
        immutablePerson.getCity().setName("Moscow");
        int hashCodeAfter = immutablePerson.hashCode();

        assertEquals(hashCodeBefore, hashCodeAfter);
    }

    @Test
    void when_changeRolesForImmutable_then_sameHashCode() {
        int hashCodeBefore = immutablePerson.hashCode();
        immutablePerson.getRoles().add("EDITOR");
        int hashCodeAfter = immutablePerson.hashCode();

        assertEquals(hashCodeBefore, hashCodeAfter);
    }
}
