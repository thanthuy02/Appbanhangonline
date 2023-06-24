package com.example.appbanhangonline.models;

import java.util.ArrayList;

public class ProductRepository {
    private static ArrayList<Product> productList = new ArrayList<>();

    public ProductRepository() {
    }

    public ProductRepository(ArrayList<Product> list){
        for(Product p : list){
            productList.add(p);
        }
    }

    public static ArrayList<Product> getProductList(){
        return productList;
    }

    public Product getProductById(Integer id){
        for ( Product p : productList) {
            if (id == p.getProductID())
                return p;
        }
        return  null;
    }
}
