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

import java.util.ArrayList;
import java.util.List;

// Đây là một adapter để kết nối dữ liệu và giao diện của danh sách danh mục.
public class CategoryAdminAdapter extends RecyclerView.Adapter<CategoryAdminAdapter.ViewHolder> {

    // khởi tạo danh sách các danh mục "categories"
    private List<Category> categories;

    // khai báo interface OnItemClickListener để lắng nghe sự kiện khi một danh mục được chọn "listener".
    private CategoryAdminAdapterListener listener;

    // Constructor CategoryAdminAdapter:
    // Khởi tạo đối tượng adapter và khởi tạo danh sách danh mục (categories) là một ArrayList rỗng.
    public CategoryAdminAdapter() {
        categories = new ArrayList<>();
    }

    // Phương thức setCategories(List<Category> categories):
    // Thiết lập danh sách danh mục và thông báo thay đổi dữ liệu bằng cách gọi notifyDataSetChanged().
    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    // Interface CategoryAdminAdapterListener:
    // Định nghĩa hai phương thức callback để lắng nghe sự kiện khi người dùng nhấp vào nút "Delete" và nút "Edit".
    public interface CategoryAdminAdapterListener {
        void onDeleteClicked(int position, Category category);

        void onEditClicked(int position, Category category);
    }

    // Thiết lập listener cho adapter.
    public void setListener(CategoryAdminAdapterListener listener) {
        this.listener = listener;
    }

    // Phương thức onCreateViewHolder(): Được gọi khi tạo một ViewHolder mới.
    // Trong phương thức này, layout ItemCategoryAdminBinding được inflate và truyền vào constructor của ViewHolder.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryAdminBinding itemCategoryAdminBinding = ItemCategoryAdminBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(itemCategoryAdminBinding);
    }

    // Phương thức onBindViewHolder(): Được gọi khi cần hiển thị dữ liệu của một mục vào ViewHolder cụ thể.
    // Trong phương thức này, dữ liệu của mục tại vị trí position được lấy ra từ danh sách categories và gán vào ViewHolder.
    // Các sự kiện click của nút "Delete" và nút "Edit" cũng được xử lý trong phương thức này.
    // Ngoài ra, sự kiện swipe của mục cũng được xử lý để hiển thị và ẩn nút.
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

    // Phương thức getItemCount(): Trả về số lượng mục trong danh sách.
    @Override
    public int getItemCount() {
        return categories.size();
    }

    // Lớp ViewHolder: Được sử dụng để lưu trữ và tham chiếu đến các thành phần giao diện của một mục trong danh sách.
    // Trong lớp này, binding ItemCategoryAdminBinding được khai báo và các dữ liệu được gán vào các thành phần giao diện.
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

        // Phương thức setDataCategory(Category category):
        // Được sử dụng để gán dữ liệu từ một đối tượng Category vào các thành phần giao diện tương ứng.
        public void setDataCategory(Category category) {
            binding.txtCategoryName.setText(category.getCategoryName());
        }
    }

//    private void animateSwipeLeft(View view) {
//        Animation animation = new TranslateAnimation(0, -view.getWidth(), 0, 0);
//        animation.setDuration(300);
//        animation.setInterpolator(new AccelerateDecelerateInterpolator());
//        view.startAnimation(animation);
//    }

    // Phương thức animateSwipeLeftItem(View view):
    // Thực hiện animation khi mục được vuốt sang trái bằng cách di chuyển nó sang trái với khoảng cách đã tính toán.
    private void animateSwipeLeftItem(View view) {
        int screenWidth = view.getResources().getDisplayMetrics().widthPixels;
        int viewWidth = view.getWidth();
        float slideDistance = screenWidth - viewWidth; // Khoảng cách trượt

        Animation animation = new TranslateAnimation(0, -slideDistance, 0, 0);
        animation.setDuration(300);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(animation);
    }

    // Phương thức showButtonWithAnimation(Button button):
    // Hiển thị nút với animation bằng cách đặt visibility của nút thành VISIBLE và thực hiện animation hiển thị.
    private void showButtonWithAnimation(final Button button) {
        button.setVisibility(View.VISIBLE);
        button.animate().alpha(1f).setDuration(300).start();
    }

    // Phương thức hideButtonWithAnimation(Button button):
    // Ẩn nút với animation bằng cách đặt visibility của nút thành GONE và thực hiện animation ẩn đi.
    private void hideButtonWithAnimation(final Button button) {
        button.animate().alpha(0f).setDuration(300).withEndAction(new Runnable() {
            @Override
            public void run() {
                button.setVisibility(View.GONE);
            }
        }).start();
    }

}
