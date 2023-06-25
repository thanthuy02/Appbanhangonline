package com.example.appbanhangonline.models;

public class Product {
    private int productID;
    private String productName;
    private String categoryName;
    private int quantity;
    private int price;
    private String image;

    public Product() {
    }

    public Product(int productID, String productName, String categoryName, int quantity, int price, String image) {
        this.productID = productID;
        this.productName = productName;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getImage() {return image; }

    public void setImage(String image) {
        this.image = image;
    }
}
