package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("Fajr")
    private Integer fajr;
    @SerializedName("Isha")
    private Integer isha;

    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    public Integer getIsha() {
        return isha;
    }

    public void setIsha(Integer isha) {
        this.isha = isha;
    }

}
