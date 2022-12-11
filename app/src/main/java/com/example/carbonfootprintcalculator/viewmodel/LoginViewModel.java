package com.example.carbonfootprintcalculator.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<Integer> uid=new MutableLiveData<>();//学号
    public MutableLiveData<String> uname=new MutableLiveData<>();
    public MutableLiveData<String> pwd=new MutableLiveData<>();
}
