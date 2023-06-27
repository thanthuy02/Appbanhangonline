package com.example.appbanhangonline.models;

public class DetailBill {
    private int billDetailID;
    private int billID;
    private int productId;
    private int quantity;
    private int price;

    public DetailBill() {
    }

    public DetailBill(int billDetailID, int billID, int productId, int quantity, int price) {
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

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DetailBill [id=" +billDetailID + ", bill_id=" + billID + ", product_id=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
