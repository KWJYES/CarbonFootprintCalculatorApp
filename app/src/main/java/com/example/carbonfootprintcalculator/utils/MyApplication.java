package com.example.carbonfootprintcalculator.utils;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        CalculatorUtil.initCoefficientMap();
        CalculatorUtil.initNumMap();
    }


    public Context getContext() {
        return context;
    }

    public static final String baseUrl="http://127.0.0.1::8080/";
    public static final String userName="";



}
