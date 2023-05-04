package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.NameDTO;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("AlphaSolutions")
public class AlphaSolutionsDataBase {
    public List<NameDTO> getNames(){
        List<NameDTO> names = new ArrayList<>(); // Oprettelse af en liste af strings, som skal indeholde names
        try{
            Connection con = DataBaseManager.getConnection(); // connection to the database
            String query = "SELECT nameString from names"; // definere den query vi vil sende til databasen
            PreparedStatement ps = con.prepareStatement(query); // klargøre query statement til SQL
            ResultSet rs = ps.executeQuery(); // resultsettet af vores query gemmes
            while (rs.next()){ // Så længe resultsettet har en linje, så tilføjes den til arraylisten ved navnet names
                names.add(new NameDTO(rs.getString(1)));
            }




        } catch (SQLException e) { // Hvis dataen ikke stemmer overens, så kastes en exception, for at indgå et crash.
            throw new RuntimeException(e);
        }
        return names;
    }

    public void addName(String nameToAdd) {
        try{
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
        try{
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

            /* We cannot use the ";" symbol in our string queries, thus I split the query in 3, with PreparedStatement for each,
            which I then execute chronologically: */

            //The first is for dropping the old instance of the table
            String dropTableQuery = "DROP TABLE IF EXISTS names";
            PreparedStatement dropTablePS = con.prepareStatement(dropTableQuery);
            dropTablePS.executeUpdate();

            //The second is for creating the new instance of the table, by doing these first 2 steps we have a clean slate
            String createTableQuery = "CREATE TABLE names (nameString varchar(255), nameID int primary key auto_increment)";
            PreparedStatement createTablePS = con.prepareStatement(createTableQuery);
            createTablePS.executeUpdate();

            //Finally I insert the test data, if the shouldHaveTestData boolean is set to true
            if (shouldHaveTestData){
                String insertDataQuery = "INSERT INTO names (nameString) VALUES ('Carl Harlang'), ('Sadek Alsukafi'), ('Tore Simonsen'), ('Simone Gottbrecht')";
                PreparedStatement insertDataPS = con.prepareStatement(insertDataQuery);
                insertDataPS.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
