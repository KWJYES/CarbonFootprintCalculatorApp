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
        coefficientMap.put("commercialOffice-computer", 1.0);
        coefficientMap.put("commercialOffice-email", 0.04);
        coefficientMap.put("commercialOffice-printer", 0.08);
        coefficientMap.put("commercialOffice-searchEngines", 0.003);
        //DailyLiving
        coefficientMap.put("DailyLiving-coalGas", 0.71);
        coefficientMap.put("DailyLiving-electric", 1.0);
        coefficientMap.put("DailyLiving-liveRubbish", 2.06);
        coefficientMap.put("DailyLiving-naturalGas", 2.91);
        coefficientMap.put("DailyLiving-oneTimeKu", 0.01);
        coefficientMap.put("DailyLiving-oneTimePaper", 3.50);
        coefficientMap.put("DailyLiving-plasticBag", 0.01);
        coefficientMap.put("DailyLiving-polyesterClothier", 25.7);
        coefficientMap.put("DailyLiving-pureCottonClothes", 7.0);
        coefficientMap.put("DailyLiving-shampoo", 0.1);
        coefficientMap.put("DailyLiving-washingDispensation", 0.3);
        coefficientMap.put("DailyLiving-washingLiquid", 0.8);
        coefficientMap.put("DailyLiving-washingMachine", 0.17);
        coefficientMap.put("DailyLiving-water", 0.91);
        coefficientMap.put("DailyLiving-waterHeater", 2.4);
        //Electronics
        coefficientMap.put("Electronics-airConditioner", 0.1);
        coefficientMap.put("Electronics-bridge", 0.83);
        coefficientMap.put("Electronics-electromagneticFurnace", 0.8);
        coefficientMap.put("Electronics-fan", 0.4);
        coefficientMap.put("Electronics-hairDrier", 0.04);
        coefficientMap.put("Electronics-oven", 0.36);
        coefficientMap.put("Electronics-TV", 0.1);
        //MealsADay
        coefficientMap.put("MealsADay-bear", 0.11);
        coefficientMap.put("MealsADay-beef", 27.0);
        coefficientMap.put("MealsADay-Broccoli", 2.0);
        coefficientMap.put("MealsADay-chicken", 1.8);
        coefficientMap.put("MealsADay-cigarette", 0.02);
        coefficientMap.put("MealsADay-coffee", 0.1);
        coefficientMap.put("MealsADay-cola", 0.05);
        coefficientMap.put("MealsADay-diningInRestaurants", 0.52);
        coefficientMap.put("MealsADay-egg", 4.80);
        coefficientMap.put("MealsADay-juice", 0.11);
        coefficientMap.put("MealsADay-lentil", 0.90);
        coefficientMap.put("MealsADay-milk", 0.47);
        coefficientMap.put("MealsADay-mutton", 39.2);
        coefficientMap.put("MealsADay-peanut", 2.50);
        coefficientMap.put("MealsADay-potato", 2.90);
        coefficientMap.put("MealsADay-rice", 2.7);
        coefficientMap.put("MealsADay-tea", 0.05);
        coefficientMap.put("MealsADay-toFu", 2.0);
        coefficientMap.put("MealsADay-tomato", 2.9);
        coefficientMap.put("MealsADay-Yogurt", 0.55);
        //tourism
        coefficientMap.put("tourism-luxuryHotel", 0.05);
        coefficientMap.put("tourism-hotel", 0.03);
        //transformation
        coefficientMap.put("transformation-bus", 0.03);
        coefficientMap.put("transformation-electricVehicle", 0.05);
        coefficientMap.put("transformation-HeightOilCar", 0.41);
        coefficientMap.put("transformation-lowOilCar", 0.27);
        coefficientMap.put("transformation-middleOilCar", 0.3);
        coefficientMap.put("transformation-plane", 0.28);
        coefficientMap.put("transformation-train", 0.1);
        coefficientMap.put("transformation-underWay", 0.1);
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
