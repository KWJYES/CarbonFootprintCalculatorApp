package com.example.carbonfootprintcalculator.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.response.network.HttpRequestManager;
import com.example.carbonfootprintcalculator.utils.CalculatorUtil;
import com.example.carbonfootprintcalculator.utils.MyApplication;

import java.util.Calendar;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> carbonNum=new MutableLiveData<>();
    public MutableLiveData<Integer> selectPos=new MutableLiveData<>();
    public CalculatorUtil calculatorUtil=new CalculatorUtil();

    public MutableLiveData<String> requestSate= new MutableLiveData<>();
    public void calculator(){
        String num = calculatorUtil.calculator()+"";
        String[] strings=num.split("\\.");
        String carbonNumber=strings[0]+"."+strings[1].substring(0,(Math.min(strings[1].length(), 3)));
        int tree = calculatorUtil.toTree(Double.parseDouble(carbonNumber));
        carbonNum.setValue(carbonNumber+"kgCO2\n约等于"+tree+"棵树");
        Item item=new Item();
        item.setUname(MyApplication.userName);
        item.setCarbonNum(Double.parseDouble(carbonNumber));
        item.setDate(getTimeString());
        item.setTreeNum(tree);
        HttpRequestManager.getInstance().insert(item,requestSate);
    }

    public void clear(){
        calculatorUtil.clear();
        carbonNum.setValue("0.0");
    }

    public void putToNumMap(String name,double num){
        calculatorUtil.updateNum(name,num);
    }

    public String getTimeString(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return year+"-"+month+"-"+day+"T"+hour+":"+minute+":"+second;
    }
}
