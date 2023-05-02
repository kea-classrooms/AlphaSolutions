package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.NameDTO;
import org.springframework.stereotype.Repository;

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
            ps.setString(1, nameToAdd);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
