package com.example.alphasolutions.repository;

import com.example.alphasolutions.DTOs.ProjectDTO;
import com.example.alphasolutions.DTOs.TasksDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.*;

@Repository("AlphaSolutions")
public class TaskRepository {
    public List<TasksDTO> getTasks(int id) {
        List<TasksDTO> tasks = new ArrayList<>();
        try {
            Connection con = DatabaseManager.getConnection();
            //Get all tasks from project with project_ID = id
            String query = "SELECT * FROM tasks LEFT JOIN deadlines USING(taskID) WHERE project_ID = ?";
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

                Date deadline_time = rs.getDate("deadline_time");
                // Create a new TasksDTO object and add it to the tasks list
                tasks.add(new TasksDTO(taskID, taskName, taskDescription, cost, totalEstimatedTime, subtasks, superTask, deadline_time));
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
            String query = "SELECT * FROM tasks LEFT JOIN deadlines USING(taskID) WHERE superTask = ?";
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
                        getSubtasks(rs.getInt("taskID")),
                        rs.getInt("superTask"),
                        rs.getDate("deadline_time")
                        ));
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
            String query = "INSERT INTO tasks(taskName, taskDescription, cost, totalEstimatedTime, project_ID, superTask) VALUE (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, taskToAdd.getTaskName());
            ps.setString(2, taskToAdd.getTaskDescription());
            ps.setInt(3, taskToAdd.getCost());
            ps.setInt(4, taskToAdd.getTotalEstimatedTime());
            ps.setInt(5, taskToAdd.getProjectID());
            ps.setInt(6, taskToAdd.getSuperTask());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                taskToAdd.setTaskID(rs.getInt(1));
            setDate(taskToAdd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate(TasksDTO taskToAdd) {
        try{
            //Get connection
            Connection con = DatabaseManager.getConnection();
            //Check whether this task is has a supertask, if so, our taskID should be the supertask's
            int taskID = taskToAdd.getSuperTask() != 0 ? taskToAdd.getSuperTask() : taskToAdd.getTaskID();

            //Check if taskID is already present in deadlines schema
            String getDeadlineQuery = "SELECT * FROM deadlines WHERE taskID = ?";
            PreparedStatement ps = con.prepareStatement(getDeadlineQuery);
            ps.setInt(1, taskID);
            ResultSet rs = ps.executeQuery();

            //Find out whether deadline should be updated or inserted
            String insertDeadlineQuery = rs.next() ? "UPDATE deadlines SET deadline_time = ? WHERE taskID = ?" : "INSERT INTO deadlines(deadline_time, taskID) VALUES (?, ?)";
            ps = con.prepareStatement(insertDeadlineQuery);
            ps.setDate(1, taskToAdd.getDeadline_time());
            //Use new taskID variable, so we make sure to update the correct row
            ps.setInt(2, taskID);
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
            String query = "SELECT * FROM tasks LEFT JOIN deadlines USING(taskID) WHERE taskID = ?";
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
                        getSubtasks(rs.getInt("taskID")),
                        rs.getInt("superTask"),
                        rs.getDate("deadline_time")); // pass the task ID to the getSubtasks method
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
    // This method updates an existing task in the database with the given properties
    public void updateTask(TasksDTO updatedTask) {
        try {
            Connection con = DatabaseManager.getConnection();
            // 'WHERE taskID = ?' specifies the condition to identify the specific task to update based on its taskID
            String query = "UPDATE tasks SET taskName = ?, taskDescription = ?, cost = ?, totalEstimatedTime = ?, superTask = ? WHERE taskID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, updatedTask.getTaskName());
            ps.setString(2, updatedTask.getTaskDescription());
            ps.setInt(3, updatedTask.getCost());
            ps.setInt(4, updatedTask.getTotalEstimatedTime());
            ps.setInt(5, updatedTask.getSuperTask());
            ps.setInt(6, updatedTask.getTaskID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // This method deletes a task with the given ID from the database
    public void deleteTask(int taskID) {
        try {
            Connection con = DatabaseManager.getConnection();
            String query = "DELETE FROM tasks WHERE taskID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, taskID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
