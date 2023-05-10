package com.example.alphasolutions.DTOs;

public class EmployeeDTO {
    private int empID;
    private String empName;

    public EmployeeDTO(){
    }

    public EmployeeDTO(int empID, String empName) {
        this.empID = empID;
        this.empName = empName;
    }


    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }



}
