package com.example.topclean.Cleaner;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private String itemName;
    private int quantity;
    private String unit;
    private String action;

    public InventoryItem(String itemName, int quantity, String unit, String action) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.action = action;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}