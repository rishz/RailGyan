package com.rishabhshukla.rubio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by rishabhshukla on 25/01/17.
 */
public class Slide1Fragment extends Fragment {

    Button btn;


    public Slide1Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slide1, container, false);
        btn = (Button) v.findViewById(R.id.btnSave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "SAVED!", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
