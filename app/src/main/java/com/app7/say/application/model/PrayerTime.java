package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrayerTime {

    @SerializedName("code")
    private Integer code;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Datum> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}