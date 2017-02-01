package com.rishabhshukla.rubio;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.gson.Gson;
import com.rishabhshukla.rubio.Models.Route;
import com.rishabhshukla.rubio.Models.TrainStatusModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;



public class TrainStatus extends AppCompatActivity {

    public static final String API_KEY = "67stoow4";

    private static final String TAG = "TrainStatus";
    private TextView startDate,position,startDateDispay,currentStnDisplay;
    private EditText etDate,etTrainNo;
    private Button btnTrainInfo;
    ArrayList<String> list;
    TrainStatusModel finaltsm1;
    //TrainStatusModel tsm;

    FloatingActionButton fabStn;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_status);
        list = new ArrayList<>();


        btnTrainInfo = (Button) findViewById(R.id.btnTrainInfo);
        etDate = (EditText) findViewById(R.id.etDate);
        etTrainNo = (EditText) findViewById(R.id.etTrainNo);
        etDate.setInputType(InputType.TYPE_NULL);
        etDate.requestFocus();

        rv= (RecyclerView) findViewById(R.id.rv);
        startDate = (TextView) findViewById(R.id.tvStartDate);
        startDateDispay = (TextView) findViewById(R.id.tvJourneyStart);
        currentStnDisplay = (TextView) findViewById(R.id.tvCurrentStnDisplay);
        position = (TextView) findViewById(R.id.tvPosition);
        fabStn = (FloatingActionButton) findViewById(R.id.fabStn);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDatePickerDialog.show();
            }
        });


        setDateTimeField();
        btnTrainInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trainNo = etTrainNo.getText().toString();
                String date=etDate.getText().toString();
                if(!(trainNo ==null) || !(date ==null)){
                    displayTrainInfo(trainNo,date);
                }else{
                    Toast.makeText(TrainStatus.this, "Enter Both Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));


    }

    private void displayTrainInfo(String trainNo, String dateOfTrain) {
        String date = dateOfTrain.substring(6,dateOfTrain.length())+dateOfTrain.substring(3,5)+dateOfTrain.substring(0,2);
        //Log.d(TAG, "displayTrainInfo: "+date);

        String myurl = "http://api.railwayapi.com/live/train/"+trainNo+"/doj/"+date+"/apikey/"+API_KEY;

        new loadUrlDataTask().execute(myurl);

    }

    class loadUrlDataTask extends AsyncTask<String,Void,String>{

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
            TrainStatusModel tsm = null;
            tsm = gson.fromJson(list,TrainStatusModel.class);
            //Log.d(TAG, "displayTrainInfo: "+tsm.getRespose_code()+"  "+tsm.getRoute().get(0).getNo());

            if(tsm.getRespose_code()!=200){
                position.setText("Train not scheduled to run on the given date.");
            }else{

                startDateDispay.setText("Journey Start Date: ");
                currentStnDisplay.setText("Current Station Info ");
                startDate.setText(tsm.getStart_date());
                position.setText(tsm.getPosition());
                 final TrainStatusModel finalTsm = tsm;
                finaltsm1 = tsm;
                fabStn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(TrainStatus.this, RouteIntent.class);
                        Route r = finalTsm.getCurrent_station();
                        //i.putExtra("route", r);
                        String myIntentJson = gson.toJson(r);
                        i.putExtra("myJson",myIntentJson);
                        i.putExtra("head","Current Station Information");
                       // i.putExtra("route",r);
                       // i.putExtra(tsm.getRoute(),"route");
                        startActivity(i);
                    }
                });
//                rv.setLayoutManager(new LinearLayoutManager(TrainStatus.this));
//                StudentAdapter stuAd= new StudentAdapter();
//                rv.setAdapter(stuAd);
//                stuAd.notifyDataSetChanged();


                rv.setLayoutManager(new LinearLayoutManager(TrainStatus.this));

                parentExpandabledapter pea = new parentExpandabledapter(TrainStatus.this, generateList());//new parentExpandabledapter(MythActivity.this, generateList());
                pea.setCustomParentAnimationViewId(R.id.list_item_expand_arrow);
                pea.setParentClickableViewAnimationDefaultDuration();
                pea.setParentAndIconExpandOnClick(true);

                rv.setAdapter(pea);


            }
        }
    }


    class StudentHolder extends RecyclerView.ViewHolder{

        TextView tvRecyStn;
        FloatingActionButton fab;

        public StudentHolder(View itemView) {
            super(itemView);
            this.tvRecyStn = (TextView) itemView.findViewById(R.id.tvRecyStnName);
            this.fab= (FloatingActionButton) itemView.findViewById(R.id.fab);
        }
    }
    class StudentAdapter extends RecyclerView.Adapter<StudentHolder>{

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View itemView=li.inflate(R.layout.recycler_layout, parent, false);


            return new StudentHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final StudentHolder holder, final int position) {

            holder.tvRecyStn.setText(finaltsm1.getRoute().get(position).getStation_().getName());
            holder.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    Intent i = new Intent(TrainStatus.this, RouteIntent.class);
                    Route r = finaltsm1.getCurrent_station();
                    //i.putExtra("route", r);
                    String myIntentJson = gson.toJson(r);
                    i.putExtra("myJsonNew",myIntentJson);
                    i.putExtra("headNew",r.getStation_().getName());
                    // i.putExtra("route",r);
                    // i.putExtra(tsm.getRoute(),"route");
                    startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount: "+finaltsm1.getRoute().size());
            return finaltsm1.getRoute().size();
        }
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public class parentExpandabledapter extends ExpandableRecyclerAdapter<parentViewHolder,childViewHolder> {
        LayoutInflater mInflater;


        public parentExpandabledapter(Context context, List<ParentObject> parentItemList) {
            super(context, parentItemList);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public parentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
            View view = mInflater.inflate(R.layout.list_item_parent, viewGroup, false);
            return new parentViewHolder(view);
        }

        @Override
        public childViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
            View view = mInflater.inflate(R.layout.list_item_child, viewGroup, false);
            return new childViewHolder(view);
        }
        @Override
        public void onBindParentViewHolder(parentViewHolder parentViewHolder, int i, Object o) {
            parentData pvn = (parentData) o;
            parentViewHolder.parentTV.setText(pvn.getData().toString());

        }

        @Override
        public void onBindChildViewHolder(childViewHolder childViewHolder, int i, Object o) {
            childData crimeChild = (childData) o;
            childViewHolder.statusChil.setText(crimeChild.getStatusChil().toString());
            childViewHolder.actDepChil.setText(crimeChild.getActDepChil().toString());

            childViewHolder.schDepChil.setText(crimeChild.getSchDepChil().toString());

            childViewHolder.distChilChil.setText(crimeChild.getDistChilChil().toString());
            childViewHolder.actArrChil.setText(crimeChild.getActArrChil().toString());

            childViewHolder.schArrChil.setText(crimeChild.getSchArrChil().toString());

            childViewHolder.stationChil.setText(crimeChild.getStationChil().toString());
            childViewHolder.hasDepartedChil.setText(crimeChild.getHasDepartedChil().toString());
            childViewHolder.hasArrivedChil.setText(crimeChild.getHasArrivedChil().toString());



        }


    }
    private ArrayList<ParentObject> generateList() {
        //Log.d(TAG, "list_title.seize=== "+list_title.size()+" list.size == "+list_data.size());
        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        for(int i=0;i<finaltsm1.getRoute().size();i++) {
            String strArr,strDep;
            parentData pData = new parentData();
            ArrayList<Object> childList = new ArrayList<>();
            pData.setData(finaltsm1.getRoute().get(i).getStation_().getName());
            // pData.setData("settedData");
            if(finaltsm1.getRoute().get(i).isHas_arrived()==true){
                strArr =  "Train has ARRIVED";
            }else{
                strArr= "Train has NOT ARRIVED";
            }
            if(finaltsm1.getRoute().get(i).isHas_departed()==true){
                strDep ="Train has DEPARTED";
            }else{
                strDep = "Train has NOT DEPARTED";
            }

            String stn = finaltsm1.getRoute().get(i).getStation_().getName()+" ("+
                    finaltsm1.getRoute().get(i).getStation_().getCode()+")";


            childList.add(new childData(strArr,strDep,stn,
                    finaltsm1.getRoute().get(i).getScharr_date()+" "+finaltsm1.getRoute().get(i).getScharr(),
                    finaltsm1.getRoute().get(i).getActarr_date()+" "+finaltsm1.getRoute().get(i).getActarr(),
                    finaltsm1.getRoute().get(i).getSchdep(),finaltsm1.getRoute().get(i).getStatus(),
                    finaltsm1.getRoute().get(i).getDistance()+" km",finaltsm1.getRoute().get(i).getActdep()

            ));

            pData.setChildObjectList(childList);
            parentObjects.add(pData);
        }
        return parentObjects;
    }


}
