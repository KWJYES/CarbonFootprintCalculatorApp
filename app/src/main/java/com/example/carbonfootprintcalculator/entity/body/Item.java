package com.example.carbonfootprintcalculator.entity.body;


import java.util.Date;

public class Item {
    private double carbonNum;
    private String date;
    private String uname;
    private int treeNum;

    public int getTreeNum() {
        return treeNum;
    }

    public void setTreeNum(int treeNum) {
        this.treeNum = treeNum;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCarbonNum() {
        return carbonNum;
    }

    public void setCarbonNum(double carbonNum) {
        this.carbonNum = carbonNum;
    }

    @Override
    public String toString() {
        return "Item{" +
                "carbonNum=" + carbonNum +
                ", date=" + date +
                ", uname='" + uname + '\'' +
                '}';
    }
}
