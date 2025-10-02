package org.example.repository;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = new Properties();
                try (FileInputStream input = new FileInputStream(
                        "db.properties")) {
                    props.load(input);
                }
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to PostgreSQL successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}

