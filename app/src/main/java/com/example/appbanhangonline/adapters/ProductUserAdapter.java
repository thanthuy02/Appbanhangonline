package com.example.appbanhangonline.adapters;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_product, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(listItem);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.productName.setText(p.getProductName());
        holder.productPrice.setText(""+p.getPrice());
        Glide.with(context).load(p.getImage()).into(holder.productImage);
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddCLick(view, p);
            }
        });
    }

    public void btnAddCLick(View view, Product p){
//        cart.addCart(p);
//        Toast.makeText(view.getContext(),"Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
        boolean r = cart.addCart(p);
        if(r == true) {
            Toast.makeText(view.getContext(),"Đã thêm vào giỏ hàng" + p.getProductID(), Toast.LENGTH_LONG).show();
        } else {
            // Số lượng vượt quá giới hạn cho phép
            Toast.makeText(view.getContext(), "Số lượng sản phẩm vượt quá giới hạn cho phép!", Toast.LENGTH_SHORT).show();
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
