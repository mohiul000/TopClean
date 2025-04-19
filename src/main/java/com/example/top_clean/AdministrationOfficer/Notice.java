package com.example.top_clean.AdministrationOfficer;

import java.time.LocalDate;

public class Notice {
    private String name;
    private String subject;
    private String description;
    private LocalDate date;

    public Notice(String name, String subject, String description, LocalDate date) {
        this.name = name;
        this.subject = subject;
        this.description = description;
        this.date = date;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    // Setters (optional, if you need them)
    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
