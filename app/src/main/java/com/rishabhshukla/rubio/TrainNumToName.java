package com.rishabhshukla.rubio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rishabhshukla.rubio.Models.Train;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TrainNumToName extends AppCompatActivity {

    public static final String API_KEY = "67stoow4";

    private static final String TAG = "TrainNumToName";

    Button btnNumToName;
    EditText etNumToName;
    Train finaltsm1;
    TextView trname, trno, monday, tuesday, wed, thurs, fri, sat,sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_num_to_name);
        trname= (TextView) findViewById(R.id.tvTrainKaNaam);
        trno= (TextView) findViewById(R.id.tvTrainKaNo);
        monday= (TextView) findViewById(R.id.tvMon);
        tuesday= (TextView) findViewById(R.id.tvTue);
        wed= (TextView) findViewById(R.id.tvWed);
        thurs= (TextView) findViewById(R.id.tvThur);
        fri= (TextView) findViewById(R.id.tvFri);
        sat= (TextView) findViewById(R.id.tvSat);
        sun= (TextView) findViewById(R.id.tvSun);

        getIntent();
        btnNumToName= (Button) findViewById(R.id.btnTrainNAME);
        etNumToName = (EditText) findViewById(R.id.etTrainNoToName);
        btnNumToName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val =etNumToName.getText().toString();


                if(val!=null){
                    getTrainInfo(val);

                    // getTrainInfo("22693");
                }
            }
        });

    }

    private void getTrainInfo(String val) {
        String myurl = "http://api.railwayapi.com/name_number/train/"+val+"/apikey/"+API_KEY;
        Log.d(TAG, "getTrainInfo: "+myurl);
        new loadUrlDataTask().execute(myurl);

    }

    class loadUrlDataTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection httpURLConnection = null;

            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {

                httpURLConnection = (HttpURLConnection) url.openConnection();

            } catch (IOException e) {
                e.printStackTrace();

            }


            try {
                InputStreamReader ir = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader br = new BufferedReader(ir);
                StringBuilder sb = new StringBuilder();
                String str = null;
                while((str=br.readLine())!=null){
                    sb.append(str);
                }
                return sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String list) {
            super.onPostExecute(list);

            final Gson gson = new Gson();
            Train tr = null;
            tr = gson.fromJson(list,Train.class);



            //Log.d(TAG, "displayTrainInfo: "+tsm.getRespose_code()+"  "+tsm.getRoute().get(0).getNo());

            Log.d(TAG, "onPostExecute: "+tr.getResponse_code()+"  "+tr.getTrain().getName()+"  "+tr.getTrain().getNumber());
            Log.d(TAG, "DAYS: "+tr.getTrain().getDays().get(0).getRuns());

            if(tr.getResponse_code()!=200){
                Toast.makeText(TrainNumToName.this, "Unable to Fetch Data", Toast.LENGTH_SHORT).show();
            }else{
                trname.setText(tr.getTrain().getName());
                trno.setText(tr.getTrain().getNumber());
                monday.setText(doesRun(tr.getTrain().getDays().get(0).getRuns()));
                tuesday.setText(doesRun(tr.getTrain().getDays().get(1).getRuns()));
                wed.setText(doesRun(tr.getTrain().getDays().get(2).getRuns()));
                thurs.setText(doesRun(tr.getTrain().getDays().get(3).getRuns()));
                fri.setText(doesRun(tr.getTrain().getDays().get(4).getRuns()));
                sat.setText(doesRun(tr.getTrain().getDays().get(5).getRuns()));
                sun.setText(doesRun(tr.getTrain().getDays().get(6).getRuns()));

            }



            }
        }
        String doesRun(String bool){
            if(bool.charAt(0)=='Y'){
                return "YES";
            }else{
                return "NO";
            }
        }
    }

