package com.example.alphasolutions.DTOs;

public class TasksDTO {
    private String taskName;
    private String taskDescription;
    private int cost;
    private int totalEstimatedTime;


    public TasksDTO(String taskName, String taskDescription, int cost, int totalEstimatedTime) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.cost = cost;
        this.totalEstimatedTime = totalEstimatedTime;
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



}
