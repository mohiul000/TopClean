package com.example.top_clean.AdministrationOfficer;

import java.util.List;

public class AdministrationOfficer {
    private List<String> employees;
    private List<String> complaints;
    private List<String> notices;

    public AdministrationOfficer(List<String> employees, List<String> complaints, List<String> notices) {
        this.employees = employees;
        this.complaints = complaints;
        this.notices = notices;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public List<String> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<String> complaints) {
        this.complaints = complaints;
    }

    public List<String> getNotices() {
        return notices;
    }

    public void setNotices(List<String> notices) {
        this.notices = notices;
    }

    @Override
    public String toString() {
        return "AdministrationOfficer{" +
                "employees=" + employees +
                ", complaints=" + complaints +
                ", notices=" + notices +
                '}';
    }
}
