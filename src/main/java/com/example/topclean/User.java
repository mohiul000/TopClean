package com.example.topclean;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private static final long serialVersionUID = 2L;
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private LocalDate dob;
    private Login.UserType type;
    private String phone;

    public User() {
    }

    public User(int id, String name, String email, String password, String address, LocalDate dob, Login.UserType type, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.dob = dob;
        this.type = type;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Login.UserType getType() {
        return type;
    }

    public void setType(Login.UserType type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}