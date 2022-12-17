package com.example.carbonfootprintcalculator.entity.body;


import java.util.Date;

public class Item {
    private double carbonNum;
    private Date date;
    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCarbonNum() {
        return carbonNum;
    }

    public void setCarbonNum(double carbonNum) {
        this.carbonNum = carbonNum;
    }
}
