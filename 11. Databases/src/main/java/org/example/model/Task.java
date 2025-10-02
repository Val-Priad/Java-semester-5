package org.example.model;

public class Task {
    private final int id;
    private final String description;
    private final int empId;

    public Task(int id, String description, int empId) {
        this.id = id;
        this.description = description;
        this.empId = empId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getEmpId() {
        return empId;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-40s | %-5d", id, description, empId);
    }
}
