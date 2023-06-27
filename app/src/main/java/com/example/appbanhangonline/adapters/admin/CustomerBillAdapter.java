package com.example.appbanhangonline.adapters.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.databinding.ItemCustomerBillBinding;
import com.example.appbanhangonline.databinding.ItemUserAdminBinding;
import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.User;

import java.util.ArrayList;
import java.util.List;

// Đây là một adapter để kết nối dữ liệu và giao diện của danh sách hóa đơn.
public class CustomerBillAdapter extends RecyclerView.Adapter<CustomerBillAdapter.ViewHolder>{

    // khởi tạo danh sách các hóa đơn "bills"
    private List<Bill> bills;

    // khai báo interface OnItemClickListener để lắng nghe sự kiện khi một hóa đơn được chọn "listener".
    private CustomerBillAdapter.OnItemClickListener listener;

    // Constructor CustomerBillAdapter:
    // Khởi tạo đối tượng adapter và khởi tạo danh sách hóa đơn (bills) là một ArrayList rỗng.
    public CustomerBillAdapter() {
        bills = new ArrayList<>();
    }

    // Phương thức setCustomerBill(List<Bill> bills):
    // Thiết lập danh sách hóa đơn và thông báo thay đổi dữ liệu bằng cách gọi notifyDataSetChanged().
    public void setCustomerBill(List<Bill> bills) {
        this.bills = bills;
        notifyDataSetChanged();
    }

    // Interface OnItemClickListener:
    // Định nghĩa một phương thức callback để lắng nghe sự kiện khi người dùng nhấp vào một hóa đơn.
    public interface OnItemClickListener {
        void onItemClick(Bill bill);
    }

    // Phương thức setOnItemClickListener(OnItemClickListener listener): Thiết lập listener cho adapter.
    public void setOnItemClickListener(CustomerBillAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    // Phương thức onCreateViewHolder(): Được gọi khi tạo một ViewHolder mới.
    // Trong phương thức này, layout ItemCustomerBillBinding được inflate và truyền vào constructor của ViewHolder.
    @Override
    public CustomerBillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCustomerBillBinding itemCustomerBillBinding = ItemCustomerBillBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new CustomerBillAdapter.ViewHolder(itemCustomerBillBinding);
    }

    // Phương thức onBindViewHolder(): Được gọi khi cần hiển thị dữ liệu của một mục vào ViewHolder cụ thể.
    // Trong phương thức này, dữ liệu của hóa đơn tại vị trí position được lấy ra từ danh sách bills và gán vào ViewHolder.
    // Sự kiện click của itemView cũng được xử lý trong phương thức này.
    @Override
    public void onBindViewHolder(final CustomerBillAdapter.ViewHolder holder, final int position) {
        Bill bill = bills.get(position);
        holder.setDataBill(bill);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(bill);
                }
            }
        });
    }

    // Phương thức getItemCount(): Trả về số lượng mục trong danh sách hóa đơn.
    @Override
    public int getItemCount() {
        return bills.size();
    }

    // Lớp ViewHolder: Được sử dụng để lưu trữ và tham chiếu đến các thành phần giao diện của một mục trong danh sách.
    // Trong lớp này, binding ItemCustomerBillBinding được khai báo và các dữ liệu được gán vào các thành phần giao diện.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemCustomerBillBinding binding;

        public ViewHolder(ItemCustomerBillBinding itemCustomerBillBinding){
            super(itemCustomerBillBinding.getRoot());
            binding = itemCustomerBillBinding;
        }

        // Phương thức setDataBill(Bill bill):
        // Được sử dụng để gán dữ liệu từ một đối tượng Bill vào các thành phần giao diện tương ứng.
        // Trong trường hợp này, tên hóa đơn và thời gian tạo hóa đơn được gán vào các TextView tương ứng trong layout.
        public void setDataBill(Bill bill) {
            binding.txtCustomerBillName.setText("Bill: #"+bill.getBillID());
            binding.txtCustomerBillTime.setText(bill.getCreatedAt());
        }
    }
}
