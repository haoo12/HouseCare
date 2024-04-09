package com.letmecare.housecare.models;

public class Day {
    String day;
    int btnAdd;

    String bookTime;

    public Day(String day, int btnAdd, String bookTime) {
        this.day = day;
        this.btnAdd = btnAdd;
        this.bookTime = bookTime;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public Day(String day, int btnAdd) {
        this.day = day;
        this.btnAdd = btnAdd;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(int btnAdd) {
        this.btnAdd = btnAdd;
    }
}
