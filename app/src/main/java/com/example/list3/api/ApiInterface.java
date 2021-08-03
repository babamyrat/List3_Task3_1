package com.example.list3.api;

import com.example.list3.response.ExampleResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    // home GET
    @GET("/api/json/v1/1/categories.php")
    Call<ExampleResponse> getAllPhotos();
}
