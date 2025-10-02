package org.example;

import org.example.controller.DepartmentController;
import org.example.controller.EmployeeController;
import org.example.controller.TaskController;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.example.repository.TaskRepository;
import org.example.view.View;

public class Main {
    public static void main(String[] args) {
        EmployeeRepository empRepo = new EmployeeRepository();
        TaskRepository taskRepo = new TaskRepository();
        DepartmentRepository  deptRepo = new DepartmentRepository();

        View view = new View();

        EmployeeController empController = new EmployeeController(empRepo, view);
        TaskController taskController = new TaskController(taskRepo, view);
        DepartmentController
                departmentController = new DepartmentController(deptRepo, view);

        view.setControllers(empController, taskController, departmentController);
        view.start();
    }
}
