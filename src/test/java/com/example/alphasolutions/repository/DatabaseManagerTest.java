package com.example.alphasolutions.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    private DatabaseManager databaseManager;
    @Test
    public void testGetConnection() throws SQLException {
        databaseManager = new DatabaseManager();
        databaseManager.setUrl("jdbc:mysql://localhost:3306/alphasolutions");
        databaseManager.setUsername("root");
        databaseManager.setPassword("root");
        Connection connection = databaseManager.getConnection();
        assertNotNull(connection);
        assertTrue(connection.isValid(5));
    }

}