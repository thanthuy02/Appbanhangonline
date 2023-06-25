package com.example.appbanhangonline.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activities.admin.ProductActivity;
import com.example.appbanhangonline.dbhandler.ProductHandler;
import com.example.appbanhangonline.models.Product;

import java.io.InputStream;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> products;

    public ProductAdapter(ProductActivity context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_product_item, parent, false);
        }

        Product product = getItem(position);
        if (product != null) {
            ImageView productImage = convertView.findViewById(R.id.imgProduct);
            TextView productName = convertView.findViewById(R.id.productName);
            TextView productCategory = convertView.findViewById(R.id.productCategory);
            TextView productQuantity = convertView.findViewById(R.id.productQuantity);
            TextView productPrice = convertView.findViewById(R.id.productPrice);

            // Hiển thị thông tin sản phẩm trong các thành phần giao diện
//            productImage.setImageURI(Uri.parse(product.getImage()));
            Glide.with(getContext())
                    .load(Uri.parse(product.getImage()))
                    .into(productImage);
            productName.setText(product.getProductName());
            productCategory.setText(product.getCategoryName());
            productQuantity.setText(String.valueOf(product.getQuantity()));
            productPrice.setText(String.valueOf(product.getPrice()));
        }

        return convertView;
    }


}
