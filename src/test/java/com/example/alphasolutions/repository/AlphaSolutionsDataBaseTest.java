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
    public void testGetNames(){
        // Test that the GetNames() method returns a list of size 4, as we insert 4 entries as test data in resetDatabase()
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

    @Test
    void deleteName() {
        //Just deletes one of the test entries in our database, and tests that the size of the list has decreased by 1
        alphaSolutionsDataBase.deleteName("Tore Simonsen");
        assertEquals(alphaSolutionsDataBase.getNames().size(), namesListInitSize - 1);
    }
}