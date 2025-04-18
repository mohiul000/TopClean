package com.example.topclean.Customer;
import java.io.Serializable;

public class Cloth implements Serializable {
    private int clothId;
    private int customerId;
    private String clothName;
    private boolean washed;

    public Cloth(int clothId, int customerId, String clothName, boolean washed) {
        this.clothId = clothId;
        this.customerId = customerId;
        this.clothName = clothName;
        this.washed = washed;
    }

    public int getClothId() {
        return clothId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getClothName() {
        return clothName;
    }

    public boolean isWashed() {
        return washed;
    }

    public void setWashed(boolean washed) {
        this.washed = washed;
    }
}