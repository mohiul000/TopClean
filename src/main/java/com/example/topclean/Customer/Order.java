package com.example.topclean.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private int customerId;
    private List<String> services;
    private String materialType;
    private String specialNote;
    private String brand;
    private int quantity;
    private LocalDate pickUpDate;
    private LocalDate dropOffDate;
    private String address;
    private String paymentType;
    private LocalDate orderDate;

    // Constructor
    public Order(int orderId, int customerId, List<String> services, String materialType, String specialNote, String brand, int quantity, LocalDate pickUpDate, LocalDate dropOffDate, String address, String paymentType, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.services = services;
        this.materialType = materialType;
        this.specialNote = specialNote;
        this.brand = brand;
        this.quantity = quantity;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.address = address;
        this.paymentType = paymentType;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDate dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}