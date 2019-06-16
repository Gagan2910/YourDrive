package com.example.admin.yourdrive;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AvailablecarsActivity extends AppCompatActivity {
android.support.v7.widget.Toolbar toolbar;
 private RecyclerView recyclerView;
 private RecyclerView.Adapter adapter;
 private List<Listitem> listitems;
    String json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablecars);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
       json_string = getIntent().getExtras().getString("json_data");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();

        try {
            JSONObject JO = new JSONObject(json_string);
            JSONArray JA = JO.getJSONArray("name");
            for (int i = 0; i < JA.length(); i++) {
                JSONObject jo = JA.getJSONObject(i);
                Listitem listitem=new Listitem(
                        jo.getString("CarModel"),jo.getString("Location"),
                        jo.getString("TripCost"),jo.getString("CarImages"),jo.getString("Pickupdate")
                ,jo.getString("Dropoffdate"),jo.getString("CarDescription"));
                listitems.add(listitem);
            }
            adapter=new RecyclerViewAdapter(listitems,getApplicationContext());
            recyclerView.setAdapter(adapter);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

       //loadRecyclerViewData();
    }



}
