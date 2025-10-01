package org.example;

import com.google.gson.Gson;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("Valid constructor stores trimmed values")
    void validPersonIsCreated() {
        Person p = new Person("  Arnold  ", "  Schwarzenegger  ", 78);

        assertEquals("Arnold", p.getName());
        assertEquals("Schwarzenegger", p.getSurname());
        assertEquals(78, p.getAge());
    }

    @Test
    @DisplayName("Name cannot be blank or exceed max length")
    void invalidNameThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person("   ", "Schwarzenegger", 78));

        String longName = "a".repeat(101);
        assertThrows(IllegalArgumentException.class,
                () -> new Person(longName, "Schwarzenegger", 78));
    }

    @Test
    @DisplayName("Surname cannot be blank or exceed max length")
    void invalidSurnameThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person("Arnold", "   ", 78));

        String longSurname = "b".repeat(101);
        assertThrows(IllegalArgumentException.class,
                () -> new Person("Arnold", longSurname, 78));
    }

    @Test
    @DisplayName("Age must be within 1..150")
    void invalidAgeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person("Arnold", "Schwarzenegger", 0));

        assertThrows(IllegalArgumentException.class,
                () -> new Person("Arnold", "Schwarzenegger", 151));
    }

    @Test
    @DisplayName("EqualsVerifier validates equals & hashCode contract")
    void equalsAndHashCodeAreValid() {
        EqualsVerifier.forClass(Person.class)
                .usingGetClass()
                .withNonnullFields("name", "surname")
                .verify();
    }

    @Test
    @DisplayName("Two persons with same fields are equal")
    void twoPersonsWithSameFieldsAreEqual() {
        Person p1 = new Person("Arnold", "Schwarzenegger", 78);
        Person p2 = new Person("Arnold", "Schwarzenegger", 78);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("Two persons with different fields are different")
    void twoPersonsWithDifferentFieldsAreNotEqual() {
        Person p1 = new Person("Arnold", "Schwarzenegger", 78);
        Person p2 = new Person("Valerii", "Priadchenko", 19);

        assertNotEquals(p1, p2);
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("Gson can serialize and deserialize Person")
    void testGson() {
        Person original = new Person("Arnold", "Schwarzenegger", 40);
        Gson gson = new Gson();
        String json = gson.toJson(original);
        Person copy = gson.fromJson(json, Person.class);
        assertEquals(original, copy);
    }

}
