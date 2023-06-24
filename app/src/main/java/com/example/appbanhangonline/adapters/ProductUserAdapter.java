package com.example.appbanhangonline.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_product, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(listItem);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final  Product p = productList.get(position);
        holder.productName.setText(p.getProductName());
        holder.productPrice.setText(""+p.getPrice());
//        holder.productImage.setImageURI(p.getImage());
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
            Toast.makeText(view.getContext(),"Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
        } else {
            // Số lượng vượt quá giới hạn cho phép
            Toast.makeText(view.getContext(), "Số lượng sản phẩm vượt quá giới hạn cho phép!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

//    public Product getProductById(int id){
//        for ( Product p : productList) {
//            if (id == p.getProductID())
//                return p;
//        }
//        return null;
//    }

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
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        ProductViewHolder holder = null;
//
//        if(view == null){
//            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//            view = inflater.inflate(R.layout.item_product, parent, false);
//
//            holder = new ProductViewHolder();
//            holder.productImage = view.findViewById(R.id.productImage);
//            holder.productName = view.findViewById(R.id.productName);
//            holder.productPrice = view.findViewById(R.id.productPrice);
//            holder.btnAdd = view.findViewById(R.id.btnAdd);
//
//            view.setTag(holder);
//        } else {
//            holder = (ProductViewHolder) view.getTag();
//        }
//
//        Product product = productList.get(position);
//
////        Glide.with(context).load(product.getImage()).into(holder.productImage);
////        holder.productName.setText(product.getProductName());
////        holder.productPrice.setText("Giá:" + product.getPrice());
//
////        byte[] productImage = product.getImage();
////        Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);
//
////        holder.productImage.setImageBitmap(bitmap);
//
//        holder.productImage.setImageURI(product.getImage());
//        holder.productName.setText(product.getProductName());
//        holder.productPrice.setText("Giá:" + product.getPrice());
//        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return view;
//    }
}
