package com.example.list3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.list3.adapter.DadabaseAdapter;
import com.example.list3.adapter.ExampleAdapter;
import com.example.list3.db.AppDatabase;
import com.example.list3.db.User;
import com.example.list3.model.ExampleModel;
import com.example.list3.viewmodel.ExampleViewModel;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ExampleModel> searchModelList = new ArrayList<>();
    List<User> userList;
    ExampleAdapter exampleAdapter;
    DadabaseAdapter dadabaseAdapter;
    ExampleViewModel searchViewModel;
    Button addNewUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewUserButton = findViewById(R.id.addNewUserButton);
        addNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NewAddDatabaseActivity.class), 100);
            }
        });


        // Api url
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);


        loadApi();

        //loadDB();


    }

    private void loadApi() {
        exampleAdapter = new ExampleAdapter(searchModelList,this);
        recyclerView.setAdapter(exampleAdapter);
        searchViewModel = ViewModelProviders.of(this).get(ExampleViewModel.class);
        searchViewModel.search();
        searchViewModel.getSearchLiveData().observe(this, exampleResponse -> {
            List<ExampleModel> models = exampleResponse.getCategories();
            exampleAdapter.addData(models);
        });
    }

    private void loadDB() {
        dadabaseAdapter = new DadabaseAdapter(userList, this);
        recyclerView.setAdapter(dadabaseAdapter);
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<User> userList = db.userDao().getAllUsers();
        dadabaseAdapter.addDataDB(userList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100){
            loadDB();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}