package com.example.alphasolutions.repository;


import com.example.alphasolutions.DTOs.NameDTO;
import com.example.alphasolutions.service.AlphaSolutionsService;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("AlphaSolutions")
public class AlphaSolutionsDataBase {
    public List<NameDTO> getNames() {
        List<NameDTO> names = new ArrayList<>(); // Creating a list of Strings that contains names
        try {
            Connection con = DataBaseManager.getConnection(); // connection to the database
            String query = "SELECT nameString from names"; // defines the query that we want to send to the database
            PreparedStatement ps = con.prepareStatement(query); // prepare query statement til SQL
            ResultSet rs = ps.executeQuery(); // Result-set of our query is saved
            while (rs.next()) { // As long as the result-set has a line, It'll add to the ArrayList by the name: names.
                names.add(new NameDTO(rs.getString(1)));
            }


        } catch (SQLException e) { // If the data does'nt match, 'throws' an exception to avoid crash.
            throw new RuntimeException(e);
        }
        return names;
    }


    public void addName(String nameToAdd) {
        try {
            Connection con = DataBaseManager.getConnection();
            String query = "INSERT INTO names(nameString) VALUE (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nameToAdd); // Replaces the first (and only) '?' in the query string with the value of 'nameToAdd'
            ps.executeUpdate(); // executeUpdate instead of executeQuery, since this is an insert/post method, and thus we don't need a ResultSet
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteName(String name) {
        try {
            Connection con = DataBaseManager.getConnection();
            //Deletes name with nameString equal to the name parameter
            String query = "DELETE FROM names WHERE nameString = ?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void resetDatabase(Boolean shouldHaveTestData) {
        //A method to reset our database, only used for testing
        try {
            Connection con = DataBaseManager.getConnection();

            // We cannot use the ";" symbol in our string queries, thus I split the query in 3, with PreparedStatement for each,
            //which I then execute chronologically:

            //The first is for dropping the old instance of the table
            String dropTableQuery = "DROP TABLE IF EXISTS names";
            PreparedStatement dropTablePS = con.prepareStatement(dropTableQuery);
            dropTablePS.executeUpdate();

            //The second is for creating the new instance of the table, by doing these first 2 steps we have a clean slate
            String createTableQuery = "CREATE TABLE names (nameString varchar(255), nameID int primary key auto_increment)";
            PreparedStatement createTablePS = con.prepareStatement(createTableQuery);
            createTablePS.executeUpdate();

            //Finally I insert the test data, if the shouldHaveTestData boolean is set to true
            if (shouldHaveTestData) {
                String insertDataQuery = "INSERT INTO names (nameString) VALUES ('Carl Harlang'), ('Sadek Alsukafi'), ('Tore Simonsen'), ('Simone Gottbrecht')";
                PreparedStatement insertDataPS = con.prepareStatement(insertDataQuery);
                insertDataPS.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


