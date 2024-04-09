package com.letmecare.housecare.models;

public class Work {
    String dayStart, dayEnd, Type, Address, Option, Total;

//    public Work(String dayStart, String dayEnd, String type, String address, String option) {
//        this.dayStart = dayStart;
//        this.dayEnd = dayEnd;
//        Type = type;
//        Address = address;
//        Option = option;
//    }

    public Work(String dayStart, String dayEnd, String type, String address, String option, String total) {
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        Type = type;
        Address = address;
        Option = option;
        Total = total;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOption() {
        return Option;
    }

    public void setOption(String option) {
        Option = option;
    }
}
