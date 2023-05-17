package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.ProjectDTO;
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
    public List<TasksDTO> getTasks(int id) {
        List<TasksDTO> tasks = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getConnection();
            String query = "Select * from tasks WHERE project_ID = ?"; // select columns
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Extract task data from the ResultSet
                int taskID = rs.getInt("taskID");
                String taskName = rs.getString("taskName");
                String taskDescription = rs.getString("taskDescription");
                int cost = rs.getInt("cost");
                int totalEstimatedTime = rs.getInt("totalEstimatedTime");
                int superTask = rs.getInt("superTask");
                // Retrieve all subtasks of the current task using the getSubtasks() method
                List<TasksDTO> subtasks = getSubtasks(rs.getInt("taskID"));
                // Create a new TasksDTO object and add it to the tasks list
                tasks.add(new TasksDTO(taskID, taskName, taskDescription, cost, totalEstimatedTime, superTask, subtasks));
            }
        } catch (SQLException e) {
            // If an SQL exception occurs, wrap it in a RuntimeException and rethrow it
            throw new RuntimeException(e);
        }
        // Return the list of tasks
        return tasks;
    }

    // This method retrieves all subtasks of a given task ID from the database and returns them as a list of TasksDTO objects
    private List<TasksDTO> getSubtasks(int taskID) {
        List<TasksDTO> subtasks = new ArrayList<>();
        try{
            Connection con = DatabaseManager.getConnection();
            String query = "SELECT * FROM tasks WHERE superTask = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, taskID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                // Create a new TasksDTO object for each subtask and add it to the subtasks list
                subtasks.add(new TasksDTO(
                        rs.getInt("taskID"),
                        rs.getString("taskName"),
                        rs.getString("taskDescription"),
                        rs.getInt("cost"),
                        rs.getInt("totalEstimatedTime"),
                        rs.getInt("superTask"),
                        null));
                // Subtasks don't have their own subtasks, so set this field to null

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subtasks;
    }

    // This method adds a new task to the database with the given properties
    public void addTask(TasksDTO taskToAdd) {
        try {
            Connection con = DatabaseManager.getConnection();
            String query = "INSERT INTO tasks(taskName, taskDescription, cost, totalEstimatedTime, project_ID, superTask) VALUE (?,?,?,?,1,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, taskToAdd.getTaskName());
            ps.setString(2, taskToAdd.getTaskDescription());
            ps.setInt(3, taskToAdd.getCost());
            ps.setInt(4, taskToAdd.getTotalEstimatedTime());
            ps.setInt(5, taskToAdd.getSuperTask());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // This method retrieves a task with the given ID from the database and returns it as a TasksDTO object
    public TasksDTO getTask(int id) {
        TasksDTO task = null;
        try{
            Connection con = DatabaseManager.getConnection();
            String query = "SELECT * FROM tasks WHERE taskID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                task = new TasksDTO(
                        rs.getInt("taskID"),
                        rs.getString("taskName"),
                        rs.getString("taskDescription"),
                        rs.getInt("cost"),
                        rs.getInt("totalEstimatedTime"),
                        rs.getInt("superTask"),
                        getSubtasks(rs.getInt("taskID"))); // pass the task ID to the getSubtasks method
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projects = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getConnection();
            String query = "SELECT * FROM project";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                projects.add(new ProjectDTO(rs.getInt("projectID"), rs.getString("projectName"), rs.getInt("managerEmployee_ID")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    public ProjectDTO getProject(int id) {
        ProjectDTO project = null;
        try {
            Connection con = DatabaseManager.getConnection();
            String query = "SELECT * FROM project WHERE projectID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                project = new ProjectDTO(rs.getInt("projectID"), rs.getString("projectName"), rs.getInt("managerEmployee_ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }
}
