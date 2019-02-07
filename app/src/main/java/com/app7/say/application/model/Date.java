package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("readable")
    private String readable;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("gregorian")
    private Gregorian gregorian;
    @SerializedName("hijri")
    private Hijri hijri;

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Gregorian getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian gregorian) {
        this.gregorian = gregorian;
    }

    public Hijri getHijri() {
        return hijri;
    }

    public void setHijri(Hijri hijri) {
        this.hijri = hijri;
    }

}
