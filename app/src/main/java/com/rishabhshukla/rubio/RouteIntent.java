package com.rishabhshukla.rubio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rishabhshukla.rubio.Models.Route;
import com.rishabhshukla.rubio.Models.Station;

public class RouteIntent extends AppCompatActivity {

    private static final String TAG = "Intent Route";
    TextView head,hasArrived,hasDeparted,station,schArr,actArr,schDep,status,dist,minsLate,actDep;
    Button btnMap,btnFIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_intent);

        Gson gson = new Gson();

        Intent i = getIntent();
        Route r;
        String heading;

        if(i.getStringExtra("headNew")==null){
             r = gson.fromJson(i.getStringExtra("myJson"),Route.class);

             heading = i.getStringExtra("head");
        }else{
             r = gson.fromJson(i.getStringExtra("myJsonNew"),Route.class);

             heading = i.getStringExtra("headNew");
        }



        head = (TextView) findViewById(R.id.tvHead);
        hasArrived = (TextView) findViewById(R.id.tvHasArrived);
        hasDeparted = (TextView) findViewById(R.id.tvHasDeparted);
        station = (TextView) findViewById(R.id.tvStn);
        schArr = (TextView) findViewById(R.id.tvSchArr);
        actArr = (TextView) findViewById(R.id.tvActArr);
        schDep = (TextView) findViewById(R.id.tvSchDep);
        actDep = (TextView) findViewById(R.id.tvActDep);
        status = (TextView) findViewById(R.id.tvStatus);
        dist = (TextView) findViewById(R.id.tvDist);

        btnMap = (Button) findViewById(R.id.btnMap);
        btnFIn = (Button) findViewById(R.id.btnFin);

        //Route r = new Route();
        btnFIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(r.isHas_arrived()==true){
            Log.d(TAG, "onCreate: "+r.isHas_arrived());
            hasArrived.setText("Train has ARRIVED");
        }else{
            hasArrived.setText("Train has NOT ARRIVED");
        }

        head.setText(heading);
        if(r.isHas_departed()==true){
            hasDeparted.setText("Train has DEPARTED");
        }else{
            hasDeparted.setText("Train has NOT DEPARTED");
        }
        final Station st = r.getStation_();
        station.setText(st.getName()+" ("+st.getCode()+")");
        status.setText(r.getStatus());
        schArr.setText(r.getScharr_date()+" "+r.getScharr());
        actArr.setText(r.getActarr_date()+" "+r.getActarr());
        schDep.setText(r.getSchdep());
        actDep.setText(r.getActdep());
        dist.setText(r.getDistance()+" km");

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RouteIntent.this,MapActivity.class);
                i.putExtra("to",st.getName());
                startActivity(i);
            }
        });


    }
}
