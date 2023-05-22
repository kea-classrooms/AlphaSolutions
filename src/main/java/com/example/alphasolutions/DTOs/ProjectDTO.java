package com.example.alphasolutions.DTOs;

public class ProjectDTO {
    // Properties of the project object

    private int projectID, managerEmployee_ID;
    private String projectName;

    // Empty constructor for TasksDTO object
    public ProjectDTO() {
    }

    // Constructor to create a new TasksDTO object with the given properties
    public ProjectDTO(int projectID,
                      String projectName,
                      int managerEmployee_ID) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.managerEmployee_ID = managerEmployee_ID;
    }

    // Getter and setter methods for the task object properties
    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getManagerEmployee_ID() {
        return managerEmployee_ID;
    }

    public void setManagerEmployee_ID(int managerEmployee_ID) {
        this.managerEmployee_ID = managerEmployee_ID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", projectID, projectName);
    }
}
