package org.example.controller;

import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.view.View;

import java.util.List;

public class TaskController {
    private final TaskRepository repo;
    private final View view;

    public TaskController(TaskRepository repo, View view) {
        this.repo = repo;
        this.view = view;
    }

    public void listTasks() {
        try {
            List<Task> tasks = repo.findAll();
            view.showMessage("Tasks:");
            System.out.printf("%-5s | %-40s | %-5s%n", "ID", "Description",
                              "EmpID");
            System.out.println(
                    "--------------------------------------------------------------");
            for (Task t : tasks) {
                System.out.println(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task) {
        try {
            repo.save(task);
            view.showMessage("Task added.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTask(Task task) {
        try {
            repo.update(task);
            view.showMessage("Task updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int id) {
        try {
            repo.delete(id);
            view.showMessage("Task deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listTasksByEmployee(int empId) {
        try {
            List<Task> tasks = repo.findByEmployee(empId);
            if (tasks.isEmpty()) {
                view.showMessage("No tasks for employee id " + empId);
            } else {
                view.showTasks(tasks);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage("Failed to load tasks for employee id " + empId);
        }
    }
}

