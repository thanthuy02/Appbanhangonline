package com.example.appbanhangonline.models;

import androidx.annotation.NonNull;

public class Category {
    private String categoryName;
    private int categoryID;

    public Category(String categoryName, int categoryID) {
        this.categoryName = categoryName;
        this.categoryID = categoryID;
    }

    public Category() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }


    @Override
    public String toString() {
        return "id: " + categoryID + " , name: " + categoryName;
    }
}
