package com.rishabhshukla.rubio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rishabhshukla on 25/01/17.
 */
public class Slide1Fragment extends Fragment {


    public Slide1Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slide1, container, false);
    }
}
