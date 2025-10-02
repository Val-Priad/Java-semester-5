package org.example.controller;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.view.View;

import java.util.List;

public class EmployeeController {
    private final EmployeeRepository repo;
    private final View view;

    public EmployeeController(EmployeeRepository repo, View view) {
        this.repo = repo;
        this.view = view;
    }

    public void listEmployees() {
        try {
            List<Employee> employees = repo.findAll();
            view.showEmployees(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee e) {
        try {
            repo.save(e);
            view.showMessage("Employee added.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateEmployee(Employee e) {
        try {
            repo.update(e);
            view.showMessage("Employee updated.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try {
            repo.delete(id);
            view.showMessage("Employee deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void listEmployeesByDepartment(int deptId) {
        try {
            List<Employee> employees = repo.findByDepartment(deptId);
            view.showEmployees(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
