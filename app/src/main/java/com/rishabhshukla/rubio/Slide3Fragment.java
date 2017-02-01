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
public class Slide3Fragment extends Fragment {

    Button btn;


    public Slide3Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slide3, container, false);
        btn = (Button) v.findViewById(R.id.BTNclk);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),TrainNumToName.class);
                startActivity(i);
            }
        });
        return v;
    }
}
