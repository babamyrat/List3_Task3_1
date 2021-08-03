package com.example.list3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.list3.adapter.ExampleAdapter;
import com.example.list3.api.ApiClient;
import com.example.list3.api.ApiInterface;
import com.example.list3.model.ExampleModel;
import com.example.list3.response.ExampleResponse;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        serviceClient();
    }
    private void serviceClient() {
        //Create handle for the RetrofitInstance interface
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ExampleResponse> call = service.getAllPhotos();
        call.enqueue(new retrofit2.Callback<ExampleResponse>() {

            @Override
            public void onResponse(@NotNull Call<ExampleResponse> call, @NotNull Response<ExampleResponse> response) {     // Response
                progressDialog.dismiss();   // progress
                if (response.isSuccessful()){
                    assert response.body() != null;
                    generateDataList(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ExampleResponse> call, @NotNull Throwable t) {       // in not on uri
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Method to generate List of data using RecyclerView with custom adapter
    private void generateDataList(List<ExampleModel> photoList) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ExampleAdapter adapter = new ExampleAdapter(photoList,this);
        recyclerView.setAdapter(adapter);


    }
}