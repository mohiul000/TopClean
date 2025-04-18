package com.example.topclean.Customer;

import com.example.topclean.User;

import java.time.LocalDate;

public class Customer extends User {


    public Customer(int id, String name, String email, String pN, String password, String type, String address, LocalDate dob) {
        super(id, name, email, pN, password, type, address, dob);
    }

    public void placeAnOrder(){
        
    }
}
