package com.example.carbonfootprintcalculator.response.network;

import androidx.lifecycle.MutableLiveData;

import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.entity.body.User;

public interface INetwork {
    void register(User user, MutableLiveData<String> state);
    void login(User user, MutableLiveData<String> state);
    void insert(Item item, MutableLiveData<String> state);
    void delete(Item item, MutableLiveData<String> state);
    void find(User user, MutableLiveData<String> state);
}
