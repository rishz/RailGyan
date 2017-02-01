package com.rishabhshukla.rubio;

/**
 * Created by rishabhshukla on 29/01/17.
 */

public class childData {
    private String hasArrivedChil,hasDepartedChil,stationChil,schArrChil,actArrChil,schDepChil,statusChil,
            distChilChil,actDepChil;

    public String getHasArrivedChil() {
        return hasArrivedChil;
    }

    public String getHasDepartedChil() {
        return hasDepartedChil;
    }

    public String getStationChil() {
        return stationChil;
    }

    public String getSchArrChil() {
        return schArrChil;
    }

    public String getActArrChil() {
        return actArrChil;
    }

    public String getSchDepChil() {
        return schDepChil;
    }

    public String getStatusChil() {
        return statusChil;
    }

    public String getDistChilChil() {
        return distChilChil;
    }

    public String getActDepChil() {
        return actDepChil;
    }

    public childData(String hasArrivedChil, String hasDepartedChil,
                     String stationChil, String schArrChil, String actArrChil,
                     String schDepChil, String statusChil, String distChilChil, String actDepChil) {
        this.hasArrivedChil = hasArrivedChil;
        this.hasDepartedChil = hasDepartedChil;
        this.stationChil = stationChil;
        this.schArrChil = schArrChil;
        this.actArrChil = actArrChil;
        this.schDepChil = schDepChil;
        this.statusChil = statusChil;
        this.distChilChil = distChilChil;
        this.actDepChil = actDepChil;
    }
}