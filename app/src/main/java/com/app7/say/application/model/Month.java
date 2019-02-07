package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Month {

    @SerializedName("number")
    private Integer number;
    @SerializedName("en")
    private String en;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

}
