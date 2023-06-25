package com.example.appbanhangonline.models;

public class Product {
    private int productID;
    private String productName;
    private int categoryID;
    private int quantity;
    private int price;
    private String image;

    public Product() {
    }

    public Product(int productID, String productName, int categoryID, int quantity, int price, String image) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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

    @Override
    public String toString() {
        return "Product [id=" + productID + ", name=" + productName + ", category=" + categoryID + ", quantity=" + quantity + ", price=" + price + ", image=" + image + "]";
    }

}
