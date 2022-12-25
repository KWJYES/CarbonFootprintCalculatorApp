package com.example.carbonfootprintcalculator.response.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.entity.body.Response;
import com.example.carbonfootprintcalculator.entity.body.User;
import com.example.carbonfootprintcalculator.response.network.retrofit_api.IService;
import com.example.carbonfootprintcalculator.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequestManager implements INetwork {

    private static IService service;

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyApplication.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(IService.class);
    }

    @Override
    public void register(User user, MutableLiveData<String> state) {
        service.register(user).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200) {
                    Response r = response.body();
                    if (r.getCode() == 200)
                        state.setValue("注册成功!");
                    else {
                        state.setValue(r.getMsg());
                    }
                } else {
                    state.setValue("注册失败！");
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                state.setValue("网络请求失败！");
            }
        });
    }

    @Override
    public void login(User user, MutableLiveData<String> state) {
        service.login(user).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200) {
                    Response r = response.body();
                    if (r.getCode() == 200)
                        state.setValue("登陆成功!");
                    else {
                        state.setValue(r.getMsg());
                    }
                } else {
                    state.setValue("登陆失败！");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                state.setValue("网络请求失败！");
                Log.d("login",user.toString());
            }
        });
    }

    @Override
    public void insert(Item item, MutableLiveData<String> state) {
        service.insert(item).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200) {
                    Response r = response.body();
                    if (r.getCode() != 200)
                        state.setValue("同步失败！");
                } else {
                    state.setValue("同步失败！");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                state.setValue("网络请求失败！");
            }
        });
    }

    @Override
    public void delete(Item item, MutableLiveData<String> state) {
        service.delete(item).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == 200) {
                    Response r = response.body();
                    if (r.getCode() == 200)
                        state.setValue("成功成功！");
                    else  state.setValue("成功失败！");
                } else {
                    state.setValue("成功失败！");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                state.setValue("网络请求失败！");
            }
        });
    }

    @Override
    public void find(User user, MutableLiveData<List<Item>> itemList, MutableLiveData<String> state) {
        service.find(user).enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, retrofit2.Response<List<Item>> response) {
                if(response.code()==200){
                    List<Item> items=response.body();
                    if(items!=null&&items.size()!=0){
                        state.setValue(MyApplication.SUSSED);
                        itemList.setValue(items);
                    }else {
                        state.setValue("暂无数据");
                        itemList.setValue(new ArrayList<>());
                    }
                }else {
                    state.setValue("网络请求错误！");
                    itemList.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                state.setValue("网络请求失败！");
            }
        });
    }
}
