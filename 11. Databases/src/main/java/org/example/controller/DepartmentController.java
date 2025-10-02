package org.example.controller;

import org.example.model.Department;
import org.example.repository.DepartmentRepository;
import org.example.view.View;

import java.sql.SQLException;
import java.util.List;

public class DepartmentController {
    private final DepartmentRepository repo;
    private final View view;

    public DepartmentController(DepartmentRepository repo, View view) {
        this.repo = repo;
        this.view = view;

    }

    public int findIdByName(String deptName) throws SQLException {
        return repo.findIdByName(deptName);
    }

    public void listDepartments() {
        try {
            List<Department> departments = repo.findAll();
            view.showDepartments(departments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
