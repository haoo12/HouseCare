package com.letmecare.housecare.models;

import android.widget.TextView;

public class Option {
    int src;
    String name;

    public Option(int src, String name) {
        this.src = src;
        this.name = name;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
