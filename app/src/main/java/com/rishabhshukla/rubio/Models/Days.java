package com.rishabhshukla.rubio.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rishabhshukla on 29/01/17.
 */

public class Days {

    @SerializedName("day-code") String daycode;
    String runs;

    public String getDaycode() {
        return daycode;
    }

    public String getRuns() {
        return runs;
    }
}
