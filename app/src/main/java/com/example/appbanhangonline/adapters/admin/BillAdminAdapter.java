package com.example.appbanhangonline.adapters.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.databinding.ItemBillAdminBinding;
import com.example.appbanhangonline.databinding.ItemCustomerBillBinding;
import com.example.appbanhangonline.models.Bill;

import java.util.ArrayList;
import java.util.List;

// Đây là một adapter để kết nối dữ liệu và giao diện của danh sách hóa đơn.
public class BillAdminAdapter extends RecyclerView.Adapter<BillAdminAdapter.ViewHolder>{
    // khởi tạo danh sách các hóa đơn "bills"
    private List<Bill> bills;

    // khai báo interface OnItemClickListener để lắng nghe sự kiện khi một hóa đơn được chọn "listener".
    private BillAdminAdapter.OnItemClickListener listener;

    // Constructor BillAdminAdapter:
    // Khởi tạo đối tượng adapter và khởi tạo danh sách hóa đơn (bills) là một ArrayList rỗng.
    public BillAdminAdapter() {
        bills = new ArrayList<>();
    }

    // Phương thức setBill(List<Bill> bills):
    // Thiết lập danh sách hóa đơn và thông báo thay đổi dữ liệu bằng cách gọi notifyDataSetChanged().
    public void setBill(List<Bill> bills) {
        this.bills = bills;
        notifyDataSetChanged();
    }

    // Interface OnItemClickListener:
    // Định nghĩa một interface để lắng nghe sự kiện khi một hóa đơn được chọn.
    public interface OnItemClickListener {
        void onItemClick(Bill bill);
    }

    // Phương thức setOnItemClickListener(BillAdminAdapter.OnItemClickListener listener):
    // Thiết lập listener để lắng nghe sự kiện khi một hóa đơn được chọn.
    public void setOnItemClickListener(BillAdminAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    // Phương thức onCreateViewHolder:
    // Tạo một ViewHolder mới cho mỗi item trong RecyclerView. Sử dụng ItemBillAdminBinding để inflate layout của mỗi item.
    @Override
    public BillAdminAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBillAdminBinding itemBillAdminBinding = ItemBillAdminBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new BillAdminAdapter.ViewHolder(itemBillAdminBinding);
    }

    // Phương thức onBindViewHolder:
    // Gắn dữ liệu từ danh sách bills vào ViewHolder tại vị trí position.
    // Thiết lập dữ liệu hiển thị trong ViewHolder thông qua phương thức setDataBill().
    @Override
    public void onBindViewHolder(final BillAdminAdapter.ViewHolder holder, final int position) {
        Bill bill = bills.get(position);
        holder.setDataBill(bill);

        // setOnClickListener cho itemView trong ViewHolder:
        // Xử lý sự kiện khi một item trong danh sách được click.
        // Nếu listener đã được thiết lập, gọi phương thức onItemClick() trên listener và truyền hóa đơn (bill) tại vị trí position.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(bill);
                }
            }
        });
    }

    // Phương thức getItemCount:
    // Trả về số lượng phần tử trong danh sách bills.
    @Override
    public int getItemCount() {
        return bills.size();
    }

    // Lớp ViewHolder:
    // Đại diện cho một item trong RecyclerView.
    // Gắn kết dữ liệu từ layout bằng cách sử dụng ItemBillAdminBinding.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemBillAdminBinding binding;

        public ViewHolder(ItemBillAdminBinding itemBillAdminBinding){
            super(itemBillAdminBinding.getRoot());
            binding = itemBillAdminBinding;
        }

        // Phương thức setDataBill() được sử dụng để thiết lập dữ liệu hiển thị cho ViewHolder,
        // bao gồm tên hóa đơn và thời gian tạo hóa đơn.
        public void setDataBill(Bill bill) {
            binding.txtBillName.setText("Bill: #"+bill.getBillID());
            binding.txtBillTime.setText(bill.getCreatedAt());
        }
    }
}
