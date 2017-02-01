package com.rishabhshukla.rubio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by rishabhshukla on 25/01/17.
 */
public class Slide2Fragment extends Fragment {

    Button btnTrainStatus;

    public Slide2Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_slide2, container, false);

        btnTrainStatus = (Button) v.findViewById(R.id.btnTrainStatus);
        btnTrainStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),TrainStatus.class);
                startActivity(i);
            }
        });


        return v;

    }
}
