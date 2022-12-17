package com.example.carbonfootprintcalculator.response.network;

import androidx.lifecycle.MutableLiveData;

import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.entity.body.User;
import com.example.carbonfootprintcalculator.utils.MyApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequestManager implements INetwork{

    private static Retrofit retrofit;

    private static HttpRequestManager httpRequestManager;

    public static HttpRequestManager getInstance() {
        if (httpRequestManager == null) {
            synchronized (HttpRequestManager.class) {
                if (null == httpRequestManager) {
                    httpRequestManager = new HttpRequestManager();
                }
            }
        }
        return httpRequestManager;
    }

    private HttpRequestManager() {
        retrofit = new Retrofit.Builder().baseUrl(MyApplication.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override
    public void register(User user, MutableLiveData<String> state) {

    }

    @Override
    public void login(User user, MutableLiveData<String> state) {

    }

    @Override
    public void insert(Item item, MutableLiveData<String> state) {

    }

    @Override
    public void delete(Item item, MutableLiveData<String> state) {

    }

    @Override
    public void find(User user, MutableLiveData<String> state) {

    }
}
