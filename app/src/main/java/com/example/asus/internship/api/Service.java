package com.example.asus.internship.api;

import com.example.asus.internship.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface Service {

    @GET
    Call<ItemResponse> getItems(@Url String url);
}