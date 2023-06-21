package com.example.appbanhangonline.models;

public class Bill {
    private int billID;
    private String billCustomerID;
    private String createdAt;
    private double billTotalPrice;


    public Bill() {
    }

    public Bill(int billID, String billCustomerID, String createdAt, double billTotalPrice) {
        this.billID = billID;
        this.billCustomerID = billCustomerID;
        this.createdAt = createdAt;
        this.billTotalPrice = billTotalPrice;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getBillCustomerID() {
        return billCustomerID;
    }

    public void setBillCustomerID(String billCustomerID) {
        this.billCustomerID = billCustomerID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getBillTotalPrice() {
        return billTotalPrice;
    }

    public void setBillTotalPrice(double billTotalPrice) {
        this.billTotalPrice = billTotalPrice;
    }
}
