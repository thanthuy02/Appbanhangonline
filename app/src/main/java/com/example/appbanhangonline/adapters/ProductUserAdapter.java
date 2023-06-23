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
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.models.Product;

import java.util.List;

public class ProductUserAdapter extends BaseAdapter {
    private Context context;

    private List<Product> productList;

    public ProductUserAdapter(Context Context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ProductViewHolder{
        public ImageView productImage;
        public TextView productName, productPrice;
        public Button btnAdd;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ProductViewHolder holder = null;

        if(view == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_product, parent, false);

            holder = new ProductViewHolder();
            holder.productImage = view.findViewById(R.id.productImage);
            holder.productName = view.findViewById(R.id.productName);
            holder.productPrice = view.findViewById(R.id.productPrice);
            holder.btnAdd = view.findViewById(R.id.btnAdd);

            view.setTag(holder);
        } else {
            holder = (ProductViewHolder) view.getTag();
        }

        Product product = productList.get(position);

//        Glide.with(context).load(product.getImage()).into(holder.productImage);
//        holder.productName.setText(product.getProductName());
//        holder.productPrice.setText("Giá:" + product.getPrice());

        byte[] productImage = product.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);

        holder.productImage.setImageBitmap(bitmap);
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText("Giá:" + product.getPrice());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
