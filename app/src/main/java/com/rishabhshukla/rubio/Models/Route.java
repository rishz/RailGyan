package com.rishabhshukla.rubio.Models;

/**
 * Created by rishabhshukla on 28/01/17.
 */

public class Route  {
    int no;
    Station station_;
    boolean has_arrived;
    boolean has_departed;
    int day;
    int distance;
    String scharr; //scheduled arrival
    String schdep; //scheduled departure
    String actarr; //actual arrival
    String actdep; //actual departure
    String scharr_date;
    String actarr_date;
    int latemin;
    String status;
    String station;

    public String getStatus() {
        return status;
    }

    public String getStation() {
        return station;
    }



    public int getNo() {
        return no;
    }

    public Station getStation_() {
        return station_;
    }

    public boolean isHas_arrived() {
        return has_arrived;
    }

    public boolean isHas_departed() {
        return has_departed;
    }

    public int getDay() {
        return day;
    }

    public int getDistance() {
        return distance;
    }

    public String getScharr() {
        return scharr;
    }

    public String getSchdep() {
        return schdep;
    }

    public String getActarr() {
        return actarr;
    }

    public String getActdep() {
        return actdep;
    }

    public String getScharr_date() {
        return scharr_date;
    }

    public String getActarr_date() {
        return actarr_date;
    }

    public int getLatemin() {
        return latemin;
    }
}
