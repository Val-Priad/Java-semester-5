package org.example.model;

public class Department {
    private final int id;
    private final String name;
    private final String phone;

    public Department(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-20s | %-15s", id, name, phone);
    }
}
