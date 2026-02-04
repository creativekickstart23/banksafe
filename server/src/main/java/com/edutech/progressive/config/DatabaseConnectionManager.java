package com.edutech.progressive.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DatabaseConnectionManager {
    private static Properties properties = new Properties();

    private static void loadProperties() throws IOException {
        properties.load(new FileInputStream("/home/coder/app/server/src/main/resources/application.properties"));
    }

    public static Connection getConnection() throws SQLException {
        try {
            loadProperties();
        } catch (IOException e) {
            System.out.println(e);
        }
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        return DriverManager.getConnection(url, username, password);
    }
}