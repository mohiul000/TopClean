package com.example.top_clean.AdministrationOfficer;

import java.io.Serializable;

public class applyOrderSupplies implements Serializable {
    private final String item;
    private final int quantity;

    // Constructor
    public applyOrderSupplies(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // toString for displaying orders in TextArea
    @Override
    public String toString() {
        return "Item: " + item + ", Quantity: " + quantity + "\n";
    }
}
