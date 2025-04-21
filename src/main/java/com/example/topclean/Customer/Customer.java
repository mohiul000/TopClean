package com.example.topclean.Customer;

import com.example.topclean.Login;
import com.example.topclean.User;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDate dateJoined;

    public Customer() {
        super();
    }


    public Customer(int id, String name, String email, String password, String address, LocalDate dob, Login.UserType type, String phone, LocalDate dateJoined) {
        super(id, name, email, password, address, dob, type, phone);
        this.dateJoined = dateJoined;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }
}
