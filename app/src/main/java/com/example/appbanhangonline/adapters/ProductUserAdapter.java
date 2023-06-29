package com.example.appbanhangonline.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.models.Cart;
import com.example.appbanhangonline.models.Product;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProductUserAdapter extends RecyclerView.Adapter<ProductUserAdapter.ProductViewHolder> {
    private Context context;

    private ArrayList<Product> productList;

    public Cart cart = new Cart();

    public ProductUserAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    // được gọi khi rv cần tạo 1 viewholder mới
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_product, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(listItem);
        return productViewHolder;
    }

    // đc gọi khi rv cần hiển thị dl của 1 item trong ds
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.productName.setText(p.getProductName());

        NumberFormat currencyFormatPrice = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedPrice = currencyFormatPrice.format(p.getPrice());
        holder.productPrice.setText(formattedPrice);

        Glide.with(context)
                .load(p.getImage())
                .into(holder.productImage);
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddCLick(view, p);
            }
        });
    }


    // user bấm nút "Thêm vào giỏ hàng"
    public void btnAddCLick(View view, Product p){
        boolean r = cart.addCart(p);
        if(r == true) {
            Toast.makeText(view.getContext(),"Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
        } else {
            if(p.getQuantity() == 0) {
                Toast.makeText(view.getContext(), "Tạm thời hết hàng!Bạn hãy xem các sản phẩm khác nhé!", Toast.LENGTH_SHORT).show();
            } else {
                // Số lượng vượt quá giới hạn cho phép
                Toast.makeText(view.getContext(), "Số lượng sản phẩm vượt quá giới hạn cho phép!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public ImageView productImage;
        public TextView productName, productPrice;
        public Button btnAdd;
        public RelativeLayout relativeLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            relativeLayout = itemView.findViewById(R.id.relativelayout);
        }
    }
}