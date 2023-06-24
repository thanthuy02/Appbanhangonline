package com.example.appbanhangonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.user.CartActivity;
import com.example.appbanhangonline.models.Cart;
import com.example.appbanhangonline.models.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private Cart cart;

    public CartAdapter(Context context, Cart cart) {
        this.context = context;
        this.cart = cart;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_product_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Integer product_id = cart.getProductByOrder(position).getProductID();
        Product p = cart.productRepository.getProductById(product_id);

        Integer amount = cart.cartList.get(product_id);
        Glide.with(context).load(p.getImage()).into(holder.productImage);
        holder.productName.setText(p.getProductName());
        holder.productPrice.setText("Giá: " + p.getPrice());
        holder.productQuantity.setText("" + amount);
        holder.lineTotalPrice.setText("" + cart.getLinePrice(p));

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean r = cart.addCart(p);
                Integer amount = cart.cartList.get(product_id);
                if(r == false){
                    Toast.makeText(v.getContext(), "Số lượng sản phẩm vượt quá giới hạn cho phép!", Toast.LENGTH_SHORT).show();
                }
                holder.productQuantity.setText("" + amount);
                holder.lineTotalPrice.setText("" + cart.getLinePrice(p));
                ((CartActivity)context).updateData();
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.removeCart(p);
                Integer amount = cart.cartList.get(p.getProductID());
                holder.productQuantity.setText("" + amount);
                holder.lineTotalPrice.setText("" + cart.getLinePrice(p));
                ((CartActivity) context).updateData();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cart.cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView productImage;

        public TextView productName, productPrice, lineTotalPrice;

        public EditText productQuantity;

        public Button btnPlus, btnMinus;

        public LinearLayoutCompat linearLayoutCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            lineTotalPrice = itemView.findViewById(R.id.lineTotalPrice);
            linearLayoutCart = itemView.findViewById(R.id.linearLayoutCart);
        }
    }
}
