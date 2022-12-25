package com.example.carbonfootprintcalculator.utils;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    private Context context;

    public static final String SUSSED ="request_sussed" ;

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

    public static final String baseUrl="http://192.168.1.77:8080/";
    public static final String userName="";



}
