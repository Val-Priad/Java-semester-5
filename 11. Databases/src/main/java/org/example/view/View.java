package org.example.view;

import org.example.controller.DepartmentController;
import org.example.controller.EmployeeController;
import org.example.controller.TaskController;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.Task;

import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner sc = new Scanner(System.in);

    private EmployeeController empController;
    private TaskController taskController;
    private DepartmentController departmentController;

    public void setControllers(EmployeeController empController,
                               TaskController taskController,
                               DepartmentController departmentController) {
        this.empController = empController;
        this.taskController = taskController;
        this.departmentController = departmentController;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - List Departments");
            System.out.println("2 - List Employees");
            System.out.println("3 - Add Employee");
            System.out.println("4 - Update Employee");
            System.out.println("5 - Delete Employee");
            System.out.println("6 - List Tasks");
            System.out.println("7 - Add Task");
            System.out.println("8 - Update Task");
            System.out.println("9 - Delete Task");
            System.out.println("10 - List Employees by Department");
            System.out.println("11 - List Tasks by Employee");
            System.out.println("0 - Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> departmentController.listDepartments();
                case 2 -> empController.listEmployees();
                case 3 -> addEmployeeMenu();
                case 4 -> updateEmployeeMenu();
                case 5 -> deleteEmployeeMenu();
                case 6 -> taskController.listTasks();
                case 7 -> addTaskMenu();
                case 8 -> updateTaskMenu();
                case 9 -> deleteTaskMenu();
                case 10 -> listEmployeesByDepartmentMenu();
                case 11 -> listTasksByEmployeeMenu();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void addEmployeeMenu() {
        System.out.print("Last Name: ");
        String ln = sc.nextLine();
        System.out.print("First Name: ");
        String fn = sc.nextLine();
        System.out.print("Position: ");
        String pos = sc.nextLine();
        System.out.print("Department name: ");
        String deptName = sc.nextLine();

        try {
            int deptId = departmentController.findIdByName(deptName);
            empController.addEmployee(
                    new Employee(0, ln, fn, pos, deptId));
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private void updateEmployeeMenu() {
        System.out.print("Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Last Name: ");
        String ln = sc.nextLine();
        System.out.print("First Name: ");
        String fn = sc.nextLine();
        System.out.print("Position: ");
        String pos = sc.nextLine();
        System.out.print("Department name: ");
        String deptName = sc.nextLine();

        try {
            int deptId = departmentController.findIdByName(deptName);
            empController.updateEmployee(
                    new Employee(id, ln, fn, pos, deptId));
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }


    private void deleteEmployeeMenu() {
        System.out.print("Employee ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        empController.deleteEmployee(id);
    }

    private void addTaskMenu() {
        System.out.print("Description: ");
        String desc = sc.nextLine();
        System.out.print("Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine();
        taskController.addTask(new Task(0, desc, empId));
    }

    private void updateTaskMenu() {
        System.out.print("Task ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();
        System.out.print("Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine();
        taskController.updateTask(new Task(id, desc, empId));
    }

    private void deleteTaskMenu() {
        System.out.print("Task ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        taskController.deleteTask(id);
    }

    public void showDepartments(List<Department> departments) {
        System.out.printf("%-5s | %-20s | %-20s%n",
                          "ID", "Name", "Phone");
        System.out.println(
                "------------------------------------------------");
        for (Department d : departments) {
            System.out.println(d);
        }
    }

    public void showEmployees(List<Employee> employees) {
        System.out.printf("%-5s | %-15s | %-20s | %-20s | %-5s%n",
                          "ID", "Last Name", "First Name", "Position", "Dept");
        System.out.println(
                "---------------------------------------------------------------------------------");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void showTasks(List<Task> tasks) {
        System.out.printf("%-5s | %-40s | %-5s%n", "ID", "Description", "EmpID");
        System.out.println("-----------------------------------------------------------");
        for (Task t : tasks) {
            System.out.println(t);
        }
    }
    private void listEmployeesByDepartmentMenu() {
        System.out.print("Department name: ");
        String deptName = sc.nextLine();
        try {
            int deptId = departmentController.findIdByName(deptName);
            empController.listEmployeesByDepartment(deptId);
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private void listTasksByEmployeeMenu() {
        System.out.print("Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        taskController.listTasksByEmployee(id);
    }

    public void showMessage(String msg) {
        System.out.println("[INFO] " + msg);
    }
}
