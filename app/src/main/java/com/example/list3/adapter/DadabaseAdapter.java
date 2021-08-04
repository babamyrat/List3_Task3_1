package com.example.list3.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.list3.R;
import com.example.list3.db.User;

import java.util.List;

public class DadabaseAdapter extends RecyclerView.Adapter<DadabaseAdapter.DbViewHolder> {
    private List<User> userList;
    private Context context;

    public DadabaseAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public DbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.example_item, parent, false);
        return new DbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbViewHolder holder, int position) {
        holder.txtName.setText(userList.get(position).idCategory);
        holder.txtTextFull.setText(userList.get(position).strCategory);

        Glide.with(context)
                .load(userList.get(position).strCategoryThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        if (userList == null) return 0;
        return userList.size();
       }

    public void addDataDB(List<User> userList) {
        this.userList = userList;
    }


    public class DbViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtTextFull;
        ImageView imageView;

        public DbViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtTextFull = itemView.findViewById(R.id.txtTextFull);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
