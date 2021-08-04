package com.example.list3.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.list3.model.ExampleModel;
import com.example.list3.repository.ExampleRepository;
import com.example.list3.response.ExampleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExampleViewModel extends ViewModel {

    private final MutableLiveData<ExampleResponse> searchResponseMutableLiveData = new MutableLiveData<>();
    private  ExampleRepository movieDetailRepository = ExampleRepository.getInstance();;

    public MutableLiveData<ExampleResponse> getSearchLiveData() {
        return searchResponseMutableLiveData;
    }

    public void search() {
        movieDetailRepository.search().enqueue(new Callback<ExampleResponse>() {
            @Override
            public void onResponse(Call<ExampleResponse> call, Response<ExampleResponse> response) {
                searchResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ExampleResponse> call, Throwable t) {
                Log.d("error", t.getLocalizedMessage());
            }
        });
    }
}

