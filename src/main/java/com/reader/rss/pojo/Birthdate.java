package com.reader.rss.pojo;

public class Birthdate {
    private int year;
    private int month;
    private int day;

    @Override
    public String toString() {
        return  year +"-" + month + "-" + day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Birthdate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
