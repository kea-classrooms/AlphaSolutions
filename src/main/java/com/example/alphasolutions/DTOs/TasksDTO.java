package com.example.alphasolutions.DTOs;

import java.sql.Date;
import java.util.List;

public class TasksDTO {
    // Properties of the task object
    private int taskID;
    private String taskName;
    private String taskDescription;
    private int cost;
    private int totalEstimatedTime;
    List<TasksDTO> subtasks;
    // ID of the task's super task (if applicable)
    private int superTask;
    private Date deadline_time;
    private int projectID;
    private String projectName;

    // Constructor to create a new TasksDTO object with the given properties
    public TasksDTO(
            int taskID,
            String taskName,
            String taskDescription,
            int cost,
            int totalEstimatedTime,
            List<TasksDTO> subtasks,
            int superTask,
            int projectID,
            String projectName,
            Date deadline_time
    ) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.cost = cost;
        this.totalEstimatedTime = totalEstimatedTime;
        this.subtasks = subtasks;
        this.superTask = superTask;
        this.deadline_time = deadline_time;
        this.projectID = projectID;
        this.projectName = projectName;
    }

    // Empty constructor for TasksDTO object
    public TasksDTO() {
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // Getter and setter methods for the task object properties
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTotalEstimatedTime() {
        return totalEstimatedTime;
    }

    public void setTotalEstimatedTime(int totalEstimatedTime) {
        this.totalEstimatedTime = totalEstimatedTime;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public List<TasksDTO> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<TasksDTO> subtasks) {
        this.subtasks = subtasks;
    }


    public int getSuperTask() {
        return superTask;
    }

    public void setSuperTask(int superTask) {
        this.superTask = superTask;
    }

    public Date getDeadline_time() {
        return deadline_time;
    }
    public void setDeadline_time(Date deadline_time) {
        this.deadline_time = deadline_time;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", taskID, taskName);
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
