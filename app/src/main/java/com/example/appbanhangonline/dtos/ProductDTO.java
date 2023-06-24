package com.example.appbanhangonline.dtos;

import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.Product;

public class ProductDTO extends Product {
    // foreign data
    private Category category;

    public ProductDTO(int productID, String productName, int categoryID, int quantity, int price, String image, Category category) {
        super(productID, productName, categoryID, quantity, price, image);
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
