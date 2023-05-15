package com.example.alphasolutions.DTOs;

import java.util.List;

public class TasksDTO {
    private int taskID;
    private String taskName;
    private String taskDescription;
    private int cost;
    private int totalEstimatedTime;
    List<TasksDTO> subtasks;

    public int getSuperTask() {
        return superTask;
    }

    public void setSuperTask(int superTask) {
        this.superTask = superTask;
    }

    int superTask;

    public TasksDTO(int taskID, String taskName, String taskDescription, int cost, int totalEstimatedTime, int superTask, List<TasksDTO> subtasks) {
        this.taskID = taskID;
        this.superTask = superTask;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.cost = cost;
        this.totalEstimatedTime = totalEstimatedTime;
        this.subtasks = subtasks;
    }

    public TasksDTO() {
    }

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

    @Override
    public String toString() {
        return String.format("%d: %s", taskID, taskName);
    }
}
