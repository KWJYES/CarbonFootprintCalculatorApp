package com.example.carbonfootprintcalculator.utils;

import com.example.carbonfootprintcalculator.entity.MainRVItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorUtil {
    public static Map<String,Double> coefficientMap=new HashMap<>();
    private static Map<String,Double> numMap=new HashMap<>();
    private double carbonNum=0;

    public void updateNum(String itemName, double num){
        numMap.put(itemName,num);
    }

    /**
     * 计算问题等价为多少颗树
     * @return 多少颗树
     */
    public double calculator(){
        for (Map.Entry<String, Double> entry : numMap.entrySet()) {
            String name = entry.getKey();
            Double num = entry.getValue();
            carbonNum+=num*coefficientMap.get(name);
        }
        return carbonNum;
    }

    public void clear(){
        carbonNum=0;
        initNumMap();
    }

    public static void initCoefficientMap() {
        //commercialOffice
        coefficientMap.put("commercialOffice-电脑(小时)", 1.0);
        coefficientMap.put("commercialOffice-邮件(封)", 0.04);
        coefficientMap.put("commercialOffice-打印纸(张)", 0.08);
        coefficientMap.put("commercialOffice-搜索引擎(次)", 0.003);
        //DailyLiving
        coefficientMap.put("DailyLiving-煤气(立方米)", 0.71);
        coefficientMap.put("DailyLiving-用电(度)", 1.0);
        coefficientMap.put("DailyLiving-生活垃圾(kg)", 2.06);
        coefficientMap.put("DailyLiving-天然气(立方米)", 2.91);
        coefficientMap.put("DailyLiving-一次性筷子(双)", 0.01);
        coefficientMap.put("DailyLiving-一次性用纸(kg)", 3.50);
        coefficientMap.put("DailyLiving-塑料袋(个)", 0.01);
        coefficientMap.put("DailyLiving-涤纶衣服(件)", 25.7);
        coefficientMap.put("DailyLiving-棉质衣服(件)", 7.0);
        coefficientMap.put("DailyLiving-洗发水(升)", 0.1);
        coefficientMap.put("DailyLiving-日用洗涤用品(升)", 0.3);
        coefficientMap.put("DailyLiving-洗衣液(升)", 0.8);
        coefficientMap.put("DailyLiving-用水(立方米)", 0.91);
        //Electronics
        coefficientMap.put("Electronics-电热水器(小时)", 2.4);
        coefficientMap.put("Electronics-空调(小时)", 0.1);
        coefficientMap.put("Electronics-冰箱(小时)", 0.83);
        coefficientMap.put("Electronics-电磁炉(小时)", 0.8);
        coefficientMap.put("Electronics-风扇(小时)", 0.4);
        coefficientMap.put("Electronics-吹风机(小时)", 0.04);
        coefficientMap.put("Electronics-烤箱(小时)", 0.36);
        coefficientMap.put("Electronics-电视(小时)", 0.1);
        coefficientMap.put("Electronics-洗衣机(小时)", 0.17);
        //MealsADay
        coefficientMap.put("MealsADay-啤酒(瓶)", 0.11);
        coefficientMap.put("MealsADay-牛肉(kg)", 27.0);
        coefficientMap.put("MealsADay-西兰花(kg)", 2.0);
        coefficientMap.put("MealsADay-鸡肉(kg)", 1.8);
        coefficientMap.put("MealsADay-香烟(包)", 0.02);
        coefficientMap.put("MealsADay-咖啡(杯)", 0.1);
        coefficientMap.put("MealsADay-可乐(杯)", 0.05);
        coefficientMap.put("MealsADay-餐厅就餐(次)", 0.52);
        coefficientMap.put("MealsADay-鸡蛋(kg)", 4.80);
        coefficientMap.put("MealsADay-果汁(杯)", 0.11);
        coefficientMap.put("MealsADay-扁豆(kg)", 0.90);
        coefficientMap.put("MealsADay-牛奶(kg)", 0.47);
        coefficientMap.put("MealsADay-羊肉(kg)", 39.2);
        coefficientMap.put("MealsADay-花生(kg)", 2.50);
        coefficientMap.put("MealsADay-土豆(kg)", 2.90);
        coefficientMap.put("MealsADay-米饭(kg)", 2.7);
        coefficientMap.put("MealsADay-茶水(杯)", 0.05);
        coefficientMap.put("MealsADay-豆腐(kg)", 2.0);
        coefficientMap.put("MealsADay-西红柿(kg)", 2.9);
        coefficientMap.put("MealsADay-酸奶(kg)", 0.55);
        //tourism
        coefficientMap.put("tourism-豪华深渊(天)", 0.05);
        coefficientMap.put("tourism-酒店(天)", 0.03);
        //transformation
        coefficientMap.put("transformation-公交车(公里)", 0.03);
        coefficientMap.put("transformation-电动车(公里)", 0.05);
        coefficientMap.put("transformation-高油耗小汽车(公里)", 0.41);
        coefficientMap.put("transformation-低油耗小汽车(公里)", 0.27);
        coefficientMap.put("transformation-中油耗小汽车(公里)", 0.3);
        coefficientMap.put("transformation-飞机(公里)", 0.28);
        coefficientMap.put("transformation-火车(公里)", 0.1);
        coefficientMap.put("transformation-地铁(公里)", 0.1);
    }

    public static void initNumMap(){
        for (String key:coefficientMap.keySet())
            numMap.put(key,0.0);
    }

    public List<MainRVItem> getRVItemList(String type){
        List<MainRVItem> result=new ArrayList<>();
        for (String key:numMap.keySet()){
            if(key.startsWith(type))
                result.add(new MainRVItem(key,numMap.get(key)));
        }
        return result;
    }

    public int toTree(double n)
    {
        if (carbonNum%111 ==0)
            return  (int) (n/111);
        else
            return  (int) (n/111)+1;
    }
}
