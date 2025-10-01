package org.example;

import java.util.Objects;

public final class Person {
    private static final int NAME_MAX_LENGTH = 100;
    private static final int SURNAME_MAX_LENGTH = 100;
    private static final int MAX_AGE = 150;

    private final String name;
    private final String surname;
    private final int age;

    public Person(String name, String surname, int age) {
        this.name = validateName(name);
        this.surname = validateSurname(surname);
        this.age = validateAge(age);
    }

    private static String validateName(String name) {
        if (name == null || name.isBlank() || name.trim().length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Name cannot be empty / can't be longer than " + NAME_MAX_LENGTH);
        }
        return name.trim();
    }

    private static String validateSurname(String surname) {
        if (surname == null || surname.isBlank() || surname.trim().length() > SURNAME_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Surname cannot be empty / can't be longer than " + SURNAME_MAX_LENGTH);
        }
        return surname.trim();
    }

    private static int validateAge(int age) {
        if (age <= 0 || age > MAX_AGE) {
            throw new IllegalArgumentException("Age must be between 1 and " + MAX_AGE);
        }
        return age;
    }

    public String getName()    { return name; }
    public String getSurname() { return surname; }
    public int getAge()        { return age; }

    @Override
    public String toString() {
        return "Person:\n\tName: " + name + "\n\tSurname: " + surname + "\n\tAge: " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return age == that.age &&
               name.equals(that.name) &&
               surname.equals(that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

}
