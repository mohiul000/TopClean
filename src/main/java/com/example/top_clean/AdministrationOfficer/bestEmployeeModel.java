/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.top_clean.AdministrationOfficer;

import java.io.Serializable;

public class bestEmployeeModel implements Serializable {
    protected String name,department;
    protected int code;

    public bestEmployeeModel(String name, String department, int code) {
        this.name = name;
        this.department = department;
        this.code = code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "The Best Employee is : " + name + "\n"
                +" Department : " + department  + "\n"
                +"Employee code : " + code;
    }



}