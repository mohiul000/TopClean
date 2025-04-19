package com.example.top_clean.AdministrationOfficer;

import java.io.Serializable;

public class bestEmployeeModel implements Serializable {
    private String empId;
    private String name;
    private String department;
    private int totalTasks;
    private int completedTasks;

    // Constructor
    public bestEmployeeModel(String empId, String name, String department, int totalTasks, int completedTasks) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
    }

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    // toString method to display employee details
    @Override
    public String toString() {
        return "Employee ID: " + empId + "\n" +
                "Name: " + name + "\n" +
                "Department: " + department + "\n" +
                "Total Tasks: " + totalTasks + "\n" +
                "Completed Tasks: " + completedTasks + "\n\n";
    }
}
