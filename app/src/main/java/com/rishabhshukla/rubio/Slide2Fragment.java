package com.rishabhshukla.rubio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rishabhshukla on 25/01/17.
 */
public class Slide2Fragment extends Fragment {


    public Slide2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slide2, container, false);
    }
}
