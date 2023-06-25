package com.example.appbanhangonline.adapters.admin;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.databinding.ItemCategoryAdminBinding;
import com.example.appbanhangonline.databinding.ItemUserAdminBinding;
import com.example.appbanhangonline.listener.OnSwipeTouchListener;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.User;

import java.util.List;

public class CategoryAdminAdapter extends RecyclerView.Adapter<CategoryAdminAdapter.ViewHolder> {

    private List<Category> categories;
    private CategoryAdminAdapterListener listener;

    public CategoryAdminAdapter(List<Category> categories) {
        this.categories = categories;
    }
    public interface CategoryAdminAdapterListener {
        void onDeleteClicked(int position, Category category);
        void onEditClicked(int position, Category category);
    }
    public void setListener(CategoryAdminAdapterListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryAdminBinding itemCategoryAdminBinding = ItemCategoryAdminBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new ViewHolder(itemCategoryAdminBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Category category = categories.get(position);
        holder.setDataCategory(category);

        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDeleteClicked(position, category);
                    hideButtonWithAnimation(holder.binding.btnDelete);
                    hideButtonWithAnimation(holder.binding.btnEdit);
                }
            }
        });

        holder.binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEditClicked(position, category);
                }
            }
        });


        // Bắt sự kiện swipe để hiển thị nút
        holder.itemView.setOnTouchListener(new OnSwipeTouchListener(holder.itemView.getContext()) {
            @Override
            public void onSwipeLeft() {
                animateSwipeLeftItem(holder.itemView); // Kích hoạt animation khi vuốt sang trái
                showButtonWithAnimation(holder.binding.btnDelete);
                showButtonWithAnimation(holder.binding.btnEdit);
            }

            @Override
            public void onSwipeRight() {
                hideButtonWithAnimation(holder.binding.btnDelete);
                hideButtonWithAnimation(holder.binding.btnEdit);
            }
        });

        // Bắt sự kiện nút action
//        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Xử lý sự kiện khi nút được nhấn
//                // Ví dụ: mở cửa sổ xem chi tiết, xoá mục, v.v.
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemCategoryAdminBinding binding;

        public ViewHolder(ItemCategoryAdminBinding itemCategoryAdminBinding) {
            super(itemCategoryAdminBinding.getRoot());
            binding = itemCategoryAdminBinding;


            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        return false;
                    }
                    return true;
                }
            });
        }

        public void setDataCategory(Category category){
            binding.txtCategoryName.setText(category.getCategoryName());
        }
    }

//    private void animateSwipeLeft(View view) {
//        Animation animation = new TranslateAnimation(0, -view.getWidth(), 0, 0);
//        animation.setDuration(300);
//        animation.setInterpolator(new AccelerateDecelerateInterpolator());
//        view.startAnimation(animation);
//    }

    private void animateSwipeLeftItem(View view) {
        int screenWidth = view.getResources().getDisplayMetrics().widthPixels;
        int viewWidth = view.getWidth();
        float slideDistance = screenWidth - viewWidth; // Khoảng cách trượt

        Animation animation = new TranslateAnimation(0, -slideDistance, 0, 0);
        animation.setDuration(300);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(animation);
    }

    private void showButtonWithAnimation(final Button button) {
        button.setVisibility(View.VISIBLE);
        button.animate().alpha(1f).setDuration(300).start();
    }

    private void hideButtonWithAnimation(final Button button) {
        button.animate().alpha(0f).setDuration(300).withEndAction(new Runnable() {
            @Override
            public void run() {
                button.setVisibility(View.GONE);
            }
        }).start();
    }

}
