package org.example;

import java.util.Objects;
import java.util.UUID;

public class Person implements Passenger {
    private String name;
    private String id;

    public Person(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
               Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
