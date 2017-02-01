package com.rishabhshukla.rubio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MapActivity extends AppCompatActivity {

    EditText etfrom,etto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        etfrom = (EditText) findViewById(R.id.etFrom);
        etto = (EditText) findViewById(R.id.etTo);

        Intent i = getIntent();

        etfrom.setText("NIZAMUDDIN STN, Delhi");
        etto.setText(i.getStringExtra("to"));
    }
}
