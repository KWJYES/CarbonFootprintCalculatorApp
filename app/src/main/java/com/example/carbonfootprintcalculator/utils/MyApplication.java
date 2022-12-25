package com.example.carbonfootprintcalculator.utils;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {
    private static Application context;

    public static final String SUSSED ="request_sussed" ;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        CalculatorUtil.initCoefficientMap();
        CalculatorUtil.initNumMap();
    }


    public static Context getContext() {
        return context;
    }

    public static final String baseUrl="http://47.113.205.12:8080/";
    public static String userName="";



}
