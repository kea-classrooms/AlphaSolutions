package com.example.alphasolutions.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseManager {
    private static Connection con = null;
    private static String url, username, password;

    //Shows which value from application.properties we want to recieve
    @Value("${spring.datasource.url}")
    public void setUrl(String url){
        DatabaseManager.url = url;
    }

    @Value("${spring.datasource.username}")
    public void setUsername(String username) {
        DatabaseManager.username = username;
    }

    @Value("${spring.datasource.password}")
    public void setPassword(String password) {
        DatabaseManager.password = password;
    }

    public static Connection getConnection() {
        if (con != null) return con; //Singleton
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }



}
