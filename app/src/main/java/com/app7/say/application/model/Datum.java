package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("timings")
    private Timings timings;
    @SerializedName("date")
    private Date date;
    @SerializedName("meta")
    private Meta meta;

    public Timings getTimings() {
        return timings;
    }

    public void setTimings(Timings timings) {
        this.timings = timings;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
