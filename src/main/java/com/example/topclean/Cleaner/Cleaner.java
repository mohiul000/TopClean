package com.example.topclean.Cleaner;

import com.example.topclean.User;
import com.example.topclean.Login;

import java.io.Serializable;
import java.time.LocalDate;

public class Cleaner extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String skills;
    private String experience;

    public Cleaner() {
        super();
    }

    public Cleaner(int id, String name, String email, String password, String address, LocalDate dob, Login.UserType type, String skills, String experience, String phone) {
        super(id, name, email, password, address, dob, type, phone);
        this.skills = skills;
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
