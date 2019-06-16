package com.example.admin.yourdrive;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ContactownerActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter2;
    private List<Ownercontactdetails> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactowner);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems = new ArrayList<>();
        new ownerdetails().execute();
    }
    class ownerdetails extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog progressDialog;
        String data="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(ContactownerActivity.this);
            progressDialog.setMessage("Loading Data..");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            StringBuilder stringBuilder=new StringBuilder();
            try
            {
                URL url=new URL(AppSettings.URL_ADDRESS+"ownerdetails.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.d("AppName","Connection Opened");
                Log.d("AppNameURL Address","Connection Opened");
                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line=null;
                while((line = reader.readLine())!=null)
                {

                    data=data+line;
                    Log.d("JSON",data);
                    //stringBuilder.append(line);
                }
                try {
                    JSONObject JO = new JSONObject(data);
                    JSONArray JA = JO.getJSONArray("name");
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject jo = JA.getJSONObject(i);
                        Ownercontactdetails listitem=new Ownercontactdetails(
                                jo.getString("CarModel"),jo.getString("Location"),
                                jo.getString("Contactnumber"),jo.getString("Profilepicture"));
                        listitems.add(listitem);
                        adapter2=new OwnerAdapter(listitems,getApplicationContext());
                        recyclerView.setAdapter(adapter2);

                    }


                } catch (JSONException e1) {
                    e1.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            //return stringBuilder.toString();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoids) {
            super.onPostExecute(aVoids);

            progressDialog.dismiss();
        }
    }
}
