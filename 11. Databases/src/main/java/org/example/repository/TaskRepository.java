package org.example.repository;

import org.example.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private final Connection conn = DBConnection.getConnection();

    public List<Task> findAll() throws SQLException {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM Task";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Task(
                        rs.getInt("task_id"),
                        rs.getString("description"),
                        rs.getInt("emp_id")));
            }
        }
        return list;
    }

    public void save(Task task) throws SQLException {
        String sql = "INSERT INTO Task(description, emp_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.getDescription());
            ps.setInt(2, task.getEmpId());
            ps.executeUpdate();
        }
    }

    public void update(Task task) throws SQLException {
        String sql = "UPDATE Task SET description=?, emp_id=? WHERE task_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task.getDescription());
            ps.setInt(2, task.getEmpId());
            ps.setInt(3, task.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Task WHERE task_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
        public List<Task> findByEmployee(int empId) throws SQLException {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE emp_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Task(
                            rs.getInt("task_id"),
                            rs.getString("description"),
                            rs.getInt("emp_id")
                    ));
                }
            }
        }
        return list;
    }
}

