package com.example.appbanhangonline.models;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.example.appbanhangonline.activities.user.CartActivity;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Product;
import com.example.appbanhangonline.models.ProductRepository;

public class Cart {
    public static Map<Integer, Integer> cartList = new HashMap<>();

    private Object keys[];

    public ProductRepository productRepository = new ProductRepository();

    private static int total_price;

    // Nếu hóa đơn trên 200k sẽ tính thuế 10%
    public int getTotal_price(){
        if(total_price > 200000) {
            return (int) (this.total_price * 1.1);
        } else {
            return this.total_price;
        }
    }


    // tổng giá tiền toàn hóa đơn
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    // khi bấm thêm/ tăng sản phẩm => tính toán lại giá cả
    public boolean addCart(Product p) {
        Integer quantity = cartList.getOrDefault(p.getProductID(), 0);
        if (quantity >= p.getQuantity()) {
            return false;
        }
        cartList.put(p.getProductID(), quantity + 1);
        total_price += p.getPrice();
        return true;
    }

    // khi giảm số lượng thì tính toán lại giá , mặc định số lượng nhỏ nhất có thể là 1
    public void removeCart(Product p) {
        Integer quantity = cartList.getOrDefault(p.getProductID(), 0);
        if (quantity > 1) {
            cartList.put(p.getProductID(), quantity - 1);
            total_price -= p.getPrice();
        }
    }

    // xóa hoàn toàn 1 sản phẩm ra khỏi giỏ hàng, dùng khi người dùng bấm vào nút btnDelete
    public void deleteProduct(Product p){
        //Integer quantity = cartList.getOrDefault(p.getProductID(), 0);
        total_price -= getLinePrice(p);
        cartList.remove(p.getProductID());
    }

    // lấy ra giá tiền của sản phẩm  * số lượng nó đang có trong giỏ
    public int getLinePrice(Product p){
        return p.getPrice() * cartList.getOrDefault(p.getProductID(), 0);
    }

    // lấy thông tin sp từ giỏ hàng dựa trên vị trí (position) của sản phẩm trong giỏ hàng
    // và sử dụng product_id để truy xuất thông tin sản phẩm từ productRepository.
    public Product getProductByOrder(Integer position){
        keys = cartList.keySet().toArray();
        Integer productId = Integer.parseInt(keys[position].toString());
        return productRepository.getProductById(productId);
    }
}
