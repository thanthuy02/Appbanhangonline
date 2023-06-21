package com.example.appbanhangonline.models;

public class DetailBill {
    private int billDetailID;
    private String billID;
    private String productId;
    private int quantity;
    private double price;

    public DetailBill() {
    }

    public DetailBill(int billDetailID, String billID, String productId, int quantity, double price) {
        this.billDetailID = billDetailID;
        this.billID = billID;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getBillDetailID() {
        return billDetailID;
    }

    public void setBillDetailID(int billDetailID) {
        this.billDetailID = billDetailID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
