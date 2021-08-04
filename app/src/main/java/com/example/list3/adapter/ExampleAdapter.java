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
import com.example.list3.model.ExampleModel;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private List<ExampleModel> dataList;
    private final Context context;

    public ExampleAdapter(List<ExampleModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExampleAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter.ExampleViewHolder holder, int position) {

        holder.txtName.setText(dataList.get(position).getIdCategory());
        holder.txtTextFull.setText(dataList.get(position).getStrCategory());

        Glide.with(context)
                .load(dataList.get(position).getStrCategoryThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (dataList == null) return 0;
        return dataList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtTextFull;
        ImageView imageView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtTextFull = itemView.findViewById(R.id.txtTextFull);
            imageView = itemView.findViewById(R.id.imageView);


        }
    }

    public void addData(List<ExampleModel> listModel) {
        if (listModel != null)
            dataList = listModel;
        else
            dataList.clear();
        notifyDataSetChanged();
    }
}
