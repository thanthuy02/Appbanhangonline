package com.example.appbanhangonline.models;

import android.net.Uri;


public class Product {
    private int productID;
    private String productName;
    private int categoryID;
    private int quantity;
    private double price;
    private Uri img_byte;
    private String productDesc;


    public Product() {
    }

    public Product(int productID, String productName, int categoryID, int quantity, double price, Uri image) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.price = price;
        this.img_byte = image;
    }

    public Product(int productID, String productName, int categoryID, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Uri getImage() {return img_byte; }


    public void setImage(Uri img_byte) {
        this.img_byte = img_byte;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

}
