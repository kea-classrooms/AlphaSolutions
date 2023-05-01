package com.example.alphasolutions.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataBaseManager {
    private static Connection con = null;
    private static String url, username, password;

    @Value("${spring.datasource.url}") //viser hvilken værdi fra application.properties vi vil trække
    public void setUrl(String url){
        DataBaseManager.url = url;
    }

    @Value("${spring.datasource.username}")
    public void setUsername(String username) {
        DataBaseManager.username = username;
    }
    @Value("${spring.datasource.password}")
    public void setPassword(String password) {
        DataBaseManager.password = password;
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
