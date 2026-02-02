package com.edutech.progressive.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionManager {

    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {

                stmt.execute("DROP TABLE IF EXISTS transactions");
                stmt.execute("DROP TABLE IF EXISTS accounts");
                stmt.execute("DROP TABLE IF EXISTS customers");

                stmt.execute("CREATE TABLE customers (" +
                             "customer_id INT AUTO_INCREMENT PRIMARY KEY, " +
                             "name VARCHAR(255), " +
                             "email VARCHAR(255), " +
                             "username VARCHAR(255), " +
                             "password VARCHAR(255), " +
                             "role VARCHAR(100))");

                stmt.execute("CREATE TABLE accounts (" +
                             "account_id INT AUTO_INCREMENT PRIMARY KEY, " +
                             "customer_id INT, " +
                             "balance DOUBLE)");

                stmt.execute("CREATE TABLE transactions (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY, " +
                             "account_id INT, " +
                             "amount DOUBLE, " +
                             "type VARCHAR(50), " +
                             "date DATE)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
