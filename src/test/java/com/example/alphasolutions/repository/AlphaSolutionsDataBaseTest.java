package com.example.alphasolutions.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AlphaSolutionsDataBaseTest {

    private AlphaSolutionsDataBase alphaSolutionsDataBase;
    private int namesListInitSize;
    @BeforeEach
    public void setUp() {
        alphaSolutionsDataBase = new AlphaSolutionsDataBase();
        alphaSolutionsDataBase.resetDatabase(true);
        namesListInitSize = alphaSolutionsDataBase.getNames().size();
    }

    @AfterEach
    public void tearDown() {
        alphaSolutionsDataBase = null;
        namesListInitSize = 0;
    }

    @Test
    public void testGetNames() throws SQLException {
        // Call the getNames method and check that the result contains the inserted name
        assertEquals(4, alphaSolutionsDataBase.getNames().size());
    }

    @Test
    public void testAddName() throws SQLException {
        // Call the addName method and check that the name was added to the database
        alphaSolutionsDataBase.addName("Therese");
        Connection con = DataBaseManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM names WHERE nameString = ?");
        ps.setString(1, "Therese");
        assertNotNull(ps.executeQuery());
        assertEquals(alphaSolutionsDataBase.getNames().size(), namesListInitSize + 1);
    }
}