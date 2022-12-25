package com.example.carbonfootprintcalculator.response.local;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.HashSet;
import java.util.Set;

public interface ISharedPreferencesRequest {
    String getAccount(Context context);
    String getPassword(Context context);
    void applyAccount(Context context,String account);
    void applyPassword(Context context,String password);
}
