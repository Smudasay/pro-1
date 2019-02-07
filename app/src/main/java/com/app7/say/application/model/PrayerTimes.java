package com.app7.say.application.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 19/11/2561.
 */

public class PrayerTimes {

    @SerializedName("data")
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("timings")
        List<Timimg> timimgs;

        public List<Timimg> getTimimgs() {
            return timimgs;
        }

        public void setTimimgs(List<Timimg> timimgs) {
            this.timimgs = timimgs;
        }
    }
    public class Timimg {
        @SerializedName("Imsak")
        private String imsak;
        @SerializedName("Fajr")
        private String fajr;
        @SerializedName("Dhuhr")
        private String dhuhr;
        @SerializedName("Asr")
        private String asr;
        @SerializedName("Maghrib")
        private String maqrib;
        @SerializedName("Isha")
        private String isha;

        public String getImsak() {
            return imsak;
        }

        public void setImsak(String imsak) {
            this.imsak = imsak;
        }

        public String getFajr() {
            return fajr;
        }

        public void setFajr(String fajr) {
            this.fajr = fajr;
        }

        public String getDhuhr() {
            return dhuhr;
        }

        public void setDhuhr(String dhuhr) {
            this.dhuhr = dhuhr;
        }

        public String getAsr() {
            return asr;
        }

        public void setAsr(String asr) {
            this.asr = asr;
        }

        public String getMaqrib() {
            return maqrib;
        }

        public void setMaqrib(String maqrib) {
            this.maqrib = maqrib;
        }

        public String getIsha() {
            return isha;
        }

        public void setIsha(String isha) {
            this.isha = isha;
        }
    }
}
