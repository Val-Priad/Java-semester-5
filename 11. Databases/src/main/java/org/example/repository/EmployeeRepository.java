package org.example.repository;

import org.example.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private final Connection conn = DBConnection.getConnection();


    public List<Employee> findAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = """
                SELECT * FROM employee;
                """;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("position"),
                        rs.getInt("dept_id")
                ));
            }
        }
        return list;
    }


    public void save(Employee emp) throws SQLException {
        String sql =
                "INSERT INTO Employee(last_name, first_name, position, dept_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getLastName());
            ps.setString(2, emp.getFirstName());
            ps.setString(3, emp.getPosition());
            ps.setInt(4, emp.getDeptId());
            ps.executeUpdate();
        }
    }

    public void update(Employee emp) throws SQLException {
        String sql =
                "UPDATE Employee SET last_name=?, first_name=?, position=?, dept_id=? WHERE emp_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getLastName());
            ps.setString(2, emp.getFirstName());
            ps.setString(3, emp.getPosition());
            ps.setInt(4, emp.getDeptId());
            ps.setInt(5, emp.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE emp_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Employee> findByDepartment(int deptId) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE dept_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("position"),
                        rs.getInt("dept_id")
                ));
            }
        }
        return list;
    }
}
