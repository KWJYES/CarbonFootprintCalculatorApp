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
    public MutableLiveData<String> uname=new MutableLiveData<>();

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
        String str_month =""+ month;
        if(month<10) str_month="0"+month;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String str_day =""+ day;
        if(day<10) str_day="0"+day;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String str_hour =""+ hour;
        if(hour<10) str_hour="0"+hour;
        int minute = calendar.get(Calendar.MINUTE);
        String str_minute =""+ minute;
        if(minute<10) str_minute="0"+minute;
        int second = calendar.get(Calendar.SECOND);
        String str_second =""+ second;
        if(second<10) str_second="0"+second;
        return year+"-"+str_month+"-"+str_day+"T"+str_hour+":"+str_minute+":"+str_second;
    }
}
