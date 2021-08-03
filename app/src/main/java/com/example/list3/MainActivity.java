package com.example.list3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.list3.adapter.ExampleAdapter;
import com.example.list3.model.ExampleModel;
import com.example.list3.viewmodel.ExampleViewModel;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ExampleModel> searchModelList = new ArrayList<>();
    ExampleAdapter adapter;
    ExampleViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ExampleAdapter(searchModelList, this);
        recyclerView.setAdapter(adapter);
        searchViewModel = ViewModelProviders.of(this).get(ExampleViewModel.class);
        searchViewModel.search();
        searchViewModel.getSearchLiveData().observe(this, exampleResponse -> {
            List<ExampleModel> models = exampleResponse.getCategories();
            adapter.addData(models);
        });
    }
}