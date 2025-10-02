package org.example.model;

public class Employee {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String position;
    private final int deptId;

    public Employee(int id, String lastName, String firstName, String position,
                    int deptId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.deptId = deptId;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPosition() {
        return position;
    }

    public int getDeptId() {
        return deptId;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-15s | %-20s | %-20s | %-5d",
                             id, lastName, firstName, position,
                             deptId);
    }
}

