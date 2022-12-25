package com.example.carbonfootprintcalculator.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.entity.body.User;
import com.example.carbonfootprintcalculator.response.network.HttpRequestManager;
import com.example.carbonfootprintcalculator.utils.MyApplication;

import java.util.List;

public class HistoryRecordViewModel extends ViewModel {
    public MutableLiveData<String> deleteSate = new MutableLiveData<>();

    public void deleteRecord(Item item) {
        HttpRequestManager.getInstance().delete(item, deleteSate);
    }


    public MutableLiveData<String> findSate = new MutableLiveData<>();
    public MutableLiveData<List<Item>> itemList = new MutableLiveData<>();
    public void findAll(){
        User user=new User();
        user.setPwd("1");
        user.setUname(MyApplication.userName);
        user.setUid(1);
        HttpRequestManager.getInstance().find(user, itemList, findSate);
    }
}
