package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

public class Designation {

    @SerializedName("abbreviated")
    private String abbreviated;
    @SerializedName("expanded")
    private String expanded;

    public String getAbbreviated() {
        return abbreviated;
    }

    public void setAbbreviated(String abbreviated) {
        this.abbreviated = abbreviated;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }

}
