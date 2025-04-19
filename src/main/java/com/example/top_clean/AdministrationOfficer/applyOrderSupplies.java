
package com.example.top_clean.AdministrationOfficer;

import java.io.Serializable;


public class applyOrderSupplies implements Serializable{
    public String itemName;
    public int itemQuantity;

    public applyOrderSupplies(String itemName, int itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "Your Order of " + itemName + "\n" +
                "with the amount  " + itemQuantity + " has been placed." + "\n"+ "\n";
    }



}
