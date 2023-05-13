package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.TasksDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("AlphaSolutions")
public class TaskRepository {
    public List<TasksDTO> getTasks() {
        List<TasksDTO> tasks = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getConnection();
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
            Connection con = DatabaseManager.getConnection();
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
