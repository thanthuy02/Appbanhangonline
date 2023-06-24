package com.example.appbanhangonline.adapters.admin;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appbanhangonline.databinding.ItemUserAdminBinding;
import com.example.appbanhangonline.models.User;

import java.util.List;

public class CustomerAdminAdapter extends RecyclerView.Adapter<CustomerAdminAdapter.ViewHolder> {

    private List<User> users;

    public CustomerAdminAdapter(List<User> users) {
        this.users = users;
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