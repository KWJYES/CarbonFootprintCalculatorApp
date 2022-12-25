package com.example.carbonfootprintcalculator.response.network.retrofit_api;

import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.entity.body.Response;
import com.example.carbonfootprintcalculator.entity.body.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IService {

    @POST("api/login")
    Call<Response> login(@Body User user);

    @POST("api/register")
    Call<Response> register(@Body User user);

    @POST("item/dele")
    Call<Response> delete(@Body Item item);

    @POST("item/insert")
    Call<Response> insert(@Body Item item);

    @POST("item/find")
    Call<List<Item>> find(@Body User user);
}
