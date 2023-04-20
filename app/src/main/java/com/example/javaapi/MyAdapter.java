package com.example.javaapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaapi.databinding.ItemLayoutBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    List<MyData> userList;
    Context context;

    public MyAdapter(List<MyData> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemLayoutBinding itembinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new MyViewHolder(itembinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        MyData data = userList.get(position);
        int NewUserId = data.getUserId();
        String Uid = String.valueOf(NewUserId);
        holder.binding.UserId.setText(Uid);
        holder.binding.Body.setText(data.getBody());
        holder.binding.Title.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;
        public MyViewHolder(ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            binding = itemLayoutBinding;
        }
    }
}
