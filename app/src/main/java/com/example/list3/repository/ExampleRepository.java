package com.example.list3.repository;

import com.example.list3.api.ApiClient;
import com.example.list3.api.ApiInterface;
import com.example.list3.response.ExampleResponse;

import retrofit2.Call;

import retrofit2.Retrofit;

public class ExampleRepository {
    private static ExampleRepository exampleRepository;
    private static ApiInterface apiInterface;

    public static ExampleRepository getInstance(){
        if (exampleRepository == null){
            exampleRepository = new ExampleRepository();
        }
        return exampleRepository;
    }

    public ExampleRepository(){
        Retrofit retrofit = ApiClient.getRetrofitInstance();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public Call<ExampleResponse> search() {
        return apiInterface.getAllPhotos();
    }
}

