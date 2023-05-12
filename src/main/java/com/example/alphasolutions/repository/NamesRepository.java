package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.EmployeeDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import com.example.alphasolutions.service.AlphaSolutionsService;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("AlphaSolutions")
public class NamesRepository {
    public List<TasksDTO> getTasks() {
        List<TasksDTO> tasks = new ArrayList<>();
        try {
            Connection con = DataBaseManager.getConnection();
            String query = "Select taskName, taskDescription, cost, totalEstimatedTime from tasks"; // select columns
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String taskName = rs.getString("taskName");
                String taskDescription = rs.getString("taskDescription");
                int cost = rs.getInt("cost");
                int totalEstimatedTime = rs.getInt("totalEstimatedTime");
                tasks.add(new TasksDTO(taskName, taskDescription, cost, totalEstimatedTime)); // pass both columns to the constructor
                //  rowCount++;
            }
            // System.out.println("Retrieved " + rowCount + " rows from employee table"); // print statement to indicate the number of rows retrieved
            //con.close(); // close connection after ResultSet is processed
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    public void addTask(String taskName, String taskDescription, int cost, int totalEstimatedTime) {
        try {
            Connection con = DataBaseManager.getConnection();
            String query = "INSERT INTO tasks(taskName, taskDescription, cost, totalEstimatedTime) VALUE (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, taskName);
            ps.setString(2, taskDescription);
            ps.setInt(3, cost);
            ps.setInt(4, totalEstimatedTime);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




   /*


   //  private static final Logger logger = LoggerFactory.getLogger(AlphaSolutionsDataBase.class);
    public List<EmployeeDTO> getEmployees() {
    //    logger.info("findAll method called");
        //System.out.println("getEmployee() method called"); // print statement to indicate that the method is being called
        List<EmployeeDTO> employees = new ArrayList<>();
        try {
            Connection con = DataBaseManager.getConnection();
            String query = "SELECT empID, empName FROM employee"; // select both columns
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            //int rowCount = 0;
            while (rs.next()) {
                int empID = rs.getInt("empID"); // retrieve empID column
                String empName = rs.getString("empName"); // retrieve empName column
                employees.add(new EmployeeDTO(empID, empName)); // pass both columns to the constructor
              //  rowCount++;
            }
           // System.out.println("Retrieved " + rowCount + " rows from employee table"); // print statement to indicate the number of rows retrieved
             con.close(); // close connection after ResultSet is processed
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
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

          We cannot use the ";" symbol in our string queries, thus I split the query in 3, with PreparedStatement for each,
            which I then execute chronologically:

            //The first is for dropping the old instance of the table


           //String dropTableQuery = "DROP TABLE IF EXISTS names";
           // PreparedStatement dropTablePS = con.prepareStatement(dropTableQuery);
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

     */


