package com.example.appbanhangonline.dtos;

import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.Product;

public class ProductDTO extends Product {
    // foreign data
    private Category category;

    public ProductDTO(int productID, String productName, int categoryID, int quantity, double price, String image, String productDesc, Category category) {
        super(productID, productName, categoryID, quantity, price, image, productDesc);
        this.category = category;
    }

    public ProductDTO() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
