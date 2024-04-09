package com.letmecare.housecare.models;

public class Information {
    int src;
    String title;

    public Information(int src, String title) {
        this.src = src;
        this.title = title;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
