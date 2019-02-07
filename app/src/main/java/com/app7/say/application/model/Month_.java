package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Month_ {

    @SerializedName("number")
    private Integer number;
    @SerializedName("en")
    private String en;
    @SerializedName("ar")
    private String ar;

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

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

}
