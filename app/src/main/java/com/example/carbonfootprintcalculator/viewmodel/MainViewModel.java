package com.example.carbonfootprintcalculator.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carbonfootprintcalculator.utils.CalculatorUtil;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> carbonNum=new MutableLiveData<>();
    public MutableLiveData<Integer> selectPos=new MutableLiveData<>();
    public CalculatorUtil calculatorUtil=new CalculatorUtil();

    public MutableLiveData<String> requestSate= new MutableLiveData<>();
    public void calculator(){
        String num = calculatorUtil.calculator()+"";
        String[] strings=num.split("\\.");
        carbonNum.setValue(strings[0]+"."+strings[1].substring(0,(Math.min(strings[1].length(), 3))));
    }

    public void clear(){
        calculatorUtil.clear();
        carbonNum.setValue("0.0");
    }

    public void putToNumMap(String name,double num){
        calculatorUtil.updateNum(name,num);
    }
}
