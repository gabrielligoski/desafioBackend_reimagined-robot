package com.example.desafiobackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiobackendApplication {

    public static void main(String[] args) {
//
//        String url = "jdbc:mysql://localhost:3306/backend_challenge";
//        String username = "root";
//        String password = "123";
//
//        System.out.println("Connecting database...");
//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("Database connected!");
//        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
//        }

        SpringApplication.run(DesafiobackendApplication.class, args);
    }
}
