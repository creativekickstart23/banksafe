package com.edutech.progressive;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankSafeApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BankSafeApplication.class, args);
    }
}
