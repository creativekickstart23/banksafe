package com.edutech.progressive.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionManager {

    // Use H2 in-memory database for tests
    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            // Load H2 driver
            Class.forName("org.h2.Driver");

            // Initialize schema
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {

                // Customers table with all required columns
                stmt.execute("CREATE TABLE IF NOT EXISTS customers (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY, " +
                             "name VARCHAR(255), " +
                             "email VARCHAR(255), " +
                             "username VARCHAR(255), " +
                             "password VARCHAR(255))");

                // Accounts table
                stmt.execute("CREATE TABLE IF NOT EXISTS accounts (" +
                             "account_id INT AUTO_INCREMENT PRIMARY KEY, " +
                             "customer_id INT, " +
                             "balance DOUBLE)");

                // Transactions table
                stmt.execute("CREATE TABLE IF NOT EXISTS transactions (" +
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

    // Static method so DAOs can keep using DatabaseConnectionManager.getConnection()
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
