package com.rishabhshukla.rubio.Models;

import java.util.ArrayList;

/**
 * Created by rishabhshukla on 29/01/17.
 */

public class trainName {

    String number;
    String name;

    public ArrayList<Days> getDays() {
        return days;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    ArrayList<Days> days;
}
