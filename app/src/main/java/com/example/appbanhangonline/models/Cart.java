package com.example.appbanhangonline.models;

import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.ProductRepository;

public class Cart {
    public static Map<Integer, Integer> cartList = new HashMap<>();

    private Object keys[];

    public ProductRepository productRepository = new ProductRepository();

    private static int total_price;

    public int getTotal_price(){
        return (int) (this.total_price * 0.9);
    }


    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public boolean addCart(Product p) {
        Integer quantity = cartList.getOrDefault(p.getProductID(), 0);
        if (quantity >= p.getQuantity()) {
            return false;
        }
        cartList.put(p.getProductID(), quantity + 1);
        total_price += p.getPrice();
        return true;
    }

    public void removeCart(Product p) {
        Integer quantity = cartList.getOrDefault(p.getProductID(), 0);
        if (quantity <= 0) return;
        cartList.put(p.getProductID(), quantity - 1);
        total_price -= p.getPrice();


//        if (quantity < 1) {
//            cartList.remove(p.getProductID());
//        } else {
//            cartList.put(p.getProductID(), quantity - 1);
//        }
//        total_price -= p.getPrice();
    }




    public int getLinePrice(Product p){
        return p.getPrice() * cartList.getOrDefault(p.getProductID(), 0);
    }

    public Product getProductByOrder(Integer position){
        keys = cartList.keySet().toArray();
        return productRepository.getProductById(Integer.parseInt(keys[position].toString()));
    }


}
