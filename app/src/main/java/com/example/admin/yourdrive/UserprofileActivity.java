package com.example.admin.yourdrive;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UserprofileActivity extends AppCompatActivity {
    TextView tvemail, tvmobile, tvpostaladdress;
    Button btnupdatedetails, btncancel;
    String json_string;
    String data = "";
    String mobile;
    String address;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
        json_string = getIntent().getExtras().getString("email");
        tvemail = (TextView) findViewById(R.id.uemail);
        tvemail.setText("User:" + json_string);
        tvmobile = (TextView) findViewById(R.id.umobile);
        tvpostaladdress = (TextView) findViewById(R.id.uaddress);
        new selectUser().execute();

        btnupdatedetails = (Button) findViewById(R.id.Update);
        btncancel = (Button) findViewById(R.id.Cancel);
        btnupdatedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new updateUser().execute();

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserprofileActivity.this, WelcomeuserActivity.class);
                intent.putExtra("email", json_string);
                startActivity(intent);
            }
        });
    }

    class selectUser extends AsyncTask<Void, Void, String> {
        String json_string;

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                //Step1:Create Url object and open connection
                URL url = new URL(AppSettings.URL_ADDRESS + "selectuser.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //Step2:Create parameters to pass
                String params = URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode("" + tvemail.getText(), "UTF-8");

                //step3:Get OutputStream
                connection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                //Step4:Write parameters and flush
                writer.write(params);
                writer.flush();
                //step5:
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                Log.d("User details", stringBuilder.toString());

            } catch (Exception e) {

            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            data = result;
            try {
                JSONObject JO = new JSONObject(data);
                JSONArray JA = JO.getJSONArray("name");
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject jo = JA.getJSONObject(i);
                    mobile = jo.getString("Mobile");
                    address = jo.getString("PostalAddress");
                }
                tvmobile.setText(mobile);
                tvpostaladdress.setText(address);

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    class updateUser extends AsyncTask<Void, Void, String> {
        String data2;
        @Override
        protected String doInBackground(Void... voids) {

            StringBuilder stringBuilder = new StringBuilder();
            try {
                //Step1:Create Url object and open connection
                URL url = new URL(AppSettings.URL_ADDRESS + "updateuser.php");
                URLConnection connection = url.openConnection();
                //Step2:Create parameters to pass
                String params = URLEncoder.encode("Mobile", "UTF-8") + "=" + URLEncoder.encode("" + tvmobile.getText(), "UTF-8");
                params += "&" + URLEncoder.encode("PostalAddress", "UTF-8") + "=" + URLEncoder.encode("" + tvpostaladdress.getText(), "UTF-8");
                //step3:Get OutputStream
                connection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                //Step4:Write parameters and flush
                writer.write(params);
                writer.flush();
                //step5:
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                Log.d("Update details", stringBuilder.toString());

            } catch (Exception e) {

            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            data2 = result;
            if(data2.equals("1"))
            {
                Toast.makeText(UserprofileActivity.this, "Details updated successful", Toast.LENGTH_LONG).show();

            }


        }
    }
}
