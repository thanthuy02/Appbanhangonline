package com.example.appbanhangonline.adapters.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appbanhangonline.databinding.ItemUserAdminBinding;
import com.example.appbanhangonline.models.Category;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

// Đây là một adapter để kết nối dữ liệu và giao diện của danh sách người dùng.
public class CustomerAdminAdapter extends RecyclerView.Adapter<CustomerAdminAdapter.ViewHolder> {

    // khởi tạo danh sách các người dùng "users"
    private List<User> users;

    // khai báo interface OnItemClickListener để lắng nghe sự kiện khi một người dùng được chọn "listener".
    private OnItemClickListener listener;

    // Constructor CustomerAdminAdapter:
    // Khởi tạo đối tượng adapter và khởi tạo danh sách người dùng (users) là một ArrayList rỗng.
    public CustomerAdminAdapter() {
        users = new ArrayList<>();
    }

    // Phương thức setCustomer(List<User> users):
    // Thiết lập danh sách người dùng và thông báo thay đổi dữ liệu bằng cách gọi notifyDataSetChanged().
    public void setCustomer(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    // Interface OnItemClickListener:
    // Định nghĩa một phương thức callback để lắng nghe sự kiện khi người dùng nhấp vào một người dùng.
    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    // Thiết lập listener cho adapter.
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    // Phương thức onCreateViewHolder(): Được gọi khi tạo một ViewHolder mới.
    // Trong phương thức này, layout ItemUserAdminBinding được inflate và truyền vào constructor của ViewHolder.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserAdminBinding itemUserAdminBinding = ItemUserAdminBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new ViewHolder(itemUserAdminBinding);
    }

    // Phương thức onBindViewHolder(): Được gọi khi cần hiển thị dữ liệu của một mục vào ViewHolder cụ thể.
    // Trong phương thức này, dữ liệu của người dùng tại vị trí position được lấy ra từ danh sách users và gán vào ViewHolder.
    // Sự kiện click của itemView cũng được xử lý trong phương thức này.
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        User user = users.get(position);
        holder.setDataUser(user);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(user);
                }
            }
        });
    }

    // Phương thức getItemCount(): Trả về số lượng mục trong danh sách người dùng.
    @Override
    public int getItemCount() {
        return users.size();
    }

    // Lớp ViewHolder: Được sử dụng để lưu trữ và tham chiếu đến các thành phần giao diện của một mục trong danh sách.
    // Trong lớp này, binding ItemUserAdminBinding được khai báo và các dữ liệu được gán vào các thành phần giao diện.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemUserAdminBinding binding;

        public ViewHolder(ItemUserAdminBinding itemUserAdminBinding){
            super(itemUserAdminBinding.getRoot());
            binding = itemUserAdminBinding;
        }

        // Phương thức setDataUser(User user):
        // Được sử dụng để gán dữ liệu từ một đối tượng User vào các thành phần giao diện tương ứng.
        public void setDataUser(User user) {
            binding.txtUserName.setText(user.getUsername());
        }
    }
}