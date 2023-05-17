package com.example.alphasolutions.DTOs;

public class ProjectDTO {
    private int projectID, managerEmployee_ID;
    private String projectName;

    public ProjectDTO() {
    }

    public ProjectDTO(int projectID,
                      String projectName,
                      int managerEmployee_ID) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.managerEmployee_ID = managerEmployee_ID;
    }

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
