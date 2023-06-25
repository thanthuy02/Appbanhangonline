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

public class CustomerAdminAdapter extends RecyclerView.Adapter<CustomerAdminAdapter.ViewHolder> {

    private List<User> users;
    private OnItemClickListener listener;
    public CustomerAdminAdapter() {
        users = new ArrayList<>();
    }

    public void setCategories(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(User user);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserAdminBinding itemUserAdminBinding = ItemUserAdminBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false
        );
        return new ViewHolder(itemUserAdminBinding);
    }

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

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemUserAdminBinding binding;

        public ViewHolder(ItemUserAdminBinding itemUserAdminBinding){
            super(itemUserAdminBinding.getRoot());
            binding = itemUserAdminBinding;
        }

        public void setDataUser(User user) {
            binding.txtUserName.setText(user.getUsername());
        }
    }
}