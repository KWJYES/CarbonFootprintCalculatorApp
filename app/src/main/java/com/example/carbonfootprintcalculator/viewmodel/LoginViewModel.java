package com.example.carbonfootprintcalculator.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carbonfootprintcalculator.entity.body.User;
import com.example.carbonfootprintcalculator.response.network.HttpRequestManager;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> uname=new MutableLiveData<>();
    public MutableLiveData<String> pwd=new MutableLiveData<>();
    public MutableLiveData<String> loginSate=new MutableLiveData<>();
    public void login(){
        User user=new User();
        user.setUid(1);
        user.setUname(uname.getValue());
        user.setPwd(pwd.getValue());
        HttpRequestManager.getInstance().login(user,loginSate);
    }
}
