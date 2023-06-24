package com.example.appbanhangonline.models;

public class Bill {
    private int billID;
    private int billCustomerID;
    private String createdAt;
    private int billTotalPrice;

    private DetailBill detailBill;



    public Bill() {
    }

    public Bill(int billID, int billCustomerID, String createdAt, int billTotalPrice) {
        this.billID = billID;
        this.billCustomerID = billCustomerID;
        this.createdAt = createdAt;
        this.billTotalPrice = billTotalPrice;
    }

    public DetailBill getDetailBill() {
        return detailBill;
    }

    public void setDetailBill(DetailBill detailBill) {
        this.detailBill = detailBill;
        detailBill.setBillID(this.billID);
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getBillCustomerID() {
        return billCustomerID;
    }

    public void setBillCustomerID(int billCustomerID) {
        this.billCustomerID = billCustomerID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getBillTotalPrice() {
        return billTotalPrice;
    }

    public void setBillTotalPrice(int billTotalPrice) {
        this.billTotalPrice = billTotalPrice;
    }
}
