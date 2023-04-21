package com.example.javaapi.NestedJson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaapi.NestedJson.ModelClasses.Nested_DataModelItem;
import com.example.javaapi.databinding.NestedjsonitemlayoutBinding;

import java.util.List;

public class Nested_Adapter extends RecyclerView.Adapter<Nested_Adapter.MyNestedView> {

    List<Nested_DataModelItem> NewUserList;
    Context context;

    public Nested_Adapter(List<Nested_DataModelItem> newUserList, Context context) {
        NewUserList = newUserList;
        this.context = context;
    }

    @NonNull
    @Override
    public Nested_Adapter.MyNestedView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NestedjsonitemlayoutBinding nestedjsonitemlayoutBinding = NestedjsonitemlayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new MyNestedView(nestedjsonitemlayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Nested_Adapter.MyNestedView holder, int position) {

        Nested_DataModelItem model = NewUserList.get(position);
        int id = model.getId();
        String StringID = String.valueOf(id);
        holder.binding.ID.setText(StringID);
        holder.binding.Name.setText(model.getName());
        holder.binding.UserName.setText(model.getUsername());
        holder.binding.Email.setText(model.getEmail());
        holder.binding.Phone.setText(model.getPhone());
        holder.binding.Website.setText(model.getWebsite());
        holder.binding.Street.setText(model.getAddress().getStreet());
        holder.binding.CompanyName.setText(model.getCompany().getName());
    }

    @Override
    public int getItemCount() {
        return NewUserList.size();
    }

    public class MyNestedView extends RecyclerView.ViewHolder {

        NestedjsonitemlayoutBinding binding;

        public MyNestedView(@NonNull NestedjsonitemlayoutBinding nestedBinding) {
            super(nestedBinding.getRoot());
            binding = nestedBinding;
        }
    }
}
