package com.rishabhshukla.rubio.Models;

import java.util.ArrayList;

/**
 * Created by rishabhshukla on 28/01/17.
 */

public class TrainStatusModel {
    int response_code;
    String error;
    String position;
    String train_number;
    ArrayList<Route> route;
    String start_date;
    Route current_station;

    public String getStart_date() {
        return start_date;
    }

    public Route getCurrent_station() {
        return current_station;
    }

    public int getRespose_code() {
        return response_code;
    }

    public String getError() {
        return error;
    }

    public String getPosition() {
        return position;
    }

    public String getTrain_number() {
        return train_number;
    }

    public ArrayList<Route> getRoute() {
        return route;
    }
}
