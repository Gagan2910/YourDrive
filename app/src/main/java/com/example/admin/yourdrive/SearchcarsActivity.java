package com.example.admin.yourdrive;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;

public class SearchcarsActivity extends AppCompatActivity {
    private static final String TAG="SearchActivity";
    private DatePickerDialog.OnDateSetListener mdatesetlistener1,mdatesetlistener2;
    int PLACE_PICKER_REQUEST=1;
    String json_string;
    public static  TextView tvpickupdate,tvdropoffdate,tvlocation;
    android.support.v7.widget.Toolbar toolbar;
Button btnsearch;
RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchcars);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
        tvpickupdate = (TextView) findViewById(R.id.txtpickupdate);
        tvdropoffdate = (TextView) findViewById(R.id.txtdropoffdate);
        tvlocation = (TextView) findViewById(R.id.txtlocation);
        layout=(RelativeLayout)findViewById(R.id.relativelayout);
        btnsearch = (Button) findViewById(R.id.btnsearch);
       //new InsertCarDetails().execute();
        tvpickupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog1 = new DatePickerDialog(SearchcarsActivity.this,
                        mdatesetlistener1, year, month, day);
                datePickerDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog1.show();
            }
        });
        tvdropoffdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog2 = new DatePickerDialog(SearchcarsActivity.this,
                        mdatesetlistener2, year, month, day);
                datePickerDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog2.show();
            }
        });
        mdatesetlistener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "/" + month + "/" + dayOfMonth;
                tvpickupdate.setText(date);

            }
        };
        mdatesetlistener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "/" + month + "/" + dayOfMonth;
                tvdropoffdate.setText(date);
            }
        };
        tvlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This is the place to call the place picker function
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(SearchcarsActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvlocation.length() == 0) {
                    tvlocation.requestFocus();
                    tvlocation.setError("Field cannot be empty");
                    //Toast.makeText(AddmemoActivity.this,"Please enter the values",Toast.LENGTH_LONG).show();
                } else if (tvpickupdate.length() == 0) {
                    tvpickupdate.requestFocus();
                    tvpickupdate.setError("Field cannot be empty");

                } else if (tvdropoffdate.length() == 0) {
                    tvdropoffdate.requestFocus();
                    tvdropoffdate.setError("Field cannot be empty");
                } else {

                    new InsertCarDetails().execute();

                }
            }
        });
    }
        class InsertCarDetails extends AsyncTask<Void, Void, String> {
            String json_string;
            @Override
            protected String doInBackground(Void... voids) {
                StringBuilder stringBuilder = new StringBuilder();
                try {

                    //Step1:Create Url object and open connection
                    URL url = new URL(AppSettings.URL_ADDRESS + "searchcars.php");
                    //  URLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    //Step2:Create parameters to pass
                    String params = URLEncoder.encode("Location", "UTF-8") + "=" + URLEncoder.encode("" + tvlocation.getText(), "UTF-8");
                    params += "&" + URLEncoder.encode("Pickupdate", "UTF-8") + "=" + URLEncoder.encode("" + tvpickupdate.getText(), "UTF-8");
                    params += "&" + URLEncoder.encode("Dropoffdate", "UTF-8") + "=" + URLEncoder.encode("" + tvdropoffdate.getText(), "UTF-8");

                    //step3:Get OutputStream
                    connection.setDoOutput(true);
                   OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    //Step4:Write parameters and flush
                   writer.write(params);
                   writer.flush();
                    //step5:
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = null;
                    // StringBuilder stringBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    Log.d("Cardetails Saved or not", stringBuilder.toString());

                } catch (Exception e) {

                }
                return stringBuilder.toString();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                TextView tv=new TextView(SearchcarsActivity.this);
                tv.setText(result);
                //layout.addView(tv);
                json_string=result;
                  if(json_string==null)
                    {
                      Toast.makeText(SearchcarsActivity.this,"Please try different location or dates",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                    Intent intent = new Intent(SearchcarsActivity.this, AvailablecarsActivity.class);
                    intent.putExtra("json_data", json_string);
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {
                            Toast.makeText(SearchcarsActivity.this, "Network available,You are connected", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SearchcarsActivity.this, "network not available", Toast.LENGTH_SHORT).show();
                        }
                    startActivity(intent);

                    //Toast.makeText(SearchcarsActivity.this,"Car details added succesfully",Toast.LENGTH_LONG).show();
                }

            }
        }

        @Override
        protected void onActivityResult(int requestCode,int resultCode,Intent data)
        {
            if(requestCode==PLACE_PICKER_REQUEST)
            {
                if(resultCode==RESULT_OK)
                {
                    Place place=PlacePicker.getPlace(SearchcarsActivity.this,data);
                    tvlocation.setText(place.getAddress());

                }
            }
        }

}

