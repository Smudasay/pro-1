package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Weekday {

    @SerializedName("en")
    private String en;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

}
