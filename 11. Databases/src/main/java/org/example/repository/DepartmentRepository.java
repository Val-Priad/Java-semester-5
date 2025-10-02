package org.example.repository;

import org.example.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    private final Connection conn = DBConnection.getConnection();

        public List<Department> findAll() throws SQLException {
        List<Department> list = new ArrayList<>();
        String sql = """
        SELECT * FROM department;
        """;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("dept_id"),
                        rs.getString("name"),
                        rs.getString("phone")
                ));
            }
        }
        return list;
    }

    public int findIdByName(String deptName) throws SQLException {
        String sql = "SELECT dept_id FROM department WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, deptName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("dept_id");
            }
        }
        throw new SQLException("Department not found: " + deptName);
    }
}
