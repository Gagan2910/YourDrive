package com.example.admin.yourdrive;

import android.app.ProgressDialog;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegisteruserActivity extends AppCompatActivity {
TextView tvfirstname,tvlastname,tvemail,tvpassword,tvconfirmpassword,tvmobile,tvpostaladdress;
Button btnregister;
RelativeLayout relativeLayoutt;
android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
        tvfirstname=(TextView)findViewById(R.id.firstname);
        tvlastname=(TextView)findViewById(R.id.lastname);
        tvemail=(TextView)findViewById(R.id.email);
        tvpassword=(TextView)findViewById(R.id.password);
        tvconfirmpassword=(TextView)findViewById(R.id.confirmpassword);
        tvmobile=(TextView)findViewById(R.id.mobile);
        tvpostaladdress=(TextView)findViewById(R.id.address);
        relativeLayoutt=(RelativeLayout)findViewById(R.id.registeruserlayout);
        btnregister=(Button)findViewById(R.id.Register);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=tvemail.getText().toString();
                if (tvfirstname.length() == 0) {
                    tvfirstname.requestFocus();
                    tvfirstname.setError("Field cannot be empty");
                    //Toast.makeText(AddmemoActivity.this,"Please enter the values",Toast.LENGTH_LONG).show();
                } else if (tvlastname.length() == 0) {
                    tvlastname.requestFocus();
                    tvlastname.setError("Field cannot be empty");
                } else if (tvemail.length() == 0) {
                    tvemail.requestFocus();
                    tvemail.setError("Field cannot be empty");
                } else if (tvpassword.length() == 0) {
                    tvpassword.requestFocus();
                    tvpassword.setError("Field cannot be empty");
                }
                else if(tvpassword.length()!=tvconfirmpassword.length())
                {
                    tvconfirmpassword.requestFocus();
                   tvconfirmpassword.setError("Password and confirm password doesn't match");
                }  else if (tvmobile.length() == 0) {
                    tvmobile.requestFocus();
                    tvmobile.setError("Field cannot be empty");
                }
                else if(tvpassword.length()<= 6)
                {
                    tvpassword.requestFocus();
                    tvpassword.setError("Password length should be greater than 6 ");
                }
                else if (!isEmailValid(email)) {
                    tvemail.setError("This email address is invalid");
                }
                else{
                    new saveUser().execute();

                }

            }

            private boolean isEmailValid(String email) {
                return email.contains("@");
            }

        });
}
    class saveUser extends AsyncTask<Void,Void,String> {
        String json_string;
        ProgressDialog progressDialog;
        String data="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(RegisteruserActivity.this);
            progressDialog.setMessage("Loading Data..");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                //Step1:Create Url object and open connection
                URL url = new URL(AppSettings.URL_ADDRESS + "registeruser.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //Step2:Create parameters to pass
                String params = URLEncoder.encode("FirstName", "UTF-8") + "=" + URLEncoder.encode("" + tvfirstname.getText(), "UTF-8");
                params += "&" + URLEncoder.encode("LastName", "UTF-8") + "=" + URLEncoder.encode("" + tvlastname.getText(), "UTF-8");
                params += "&" + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode("" + tvemail.getText(), "UTF-8");
                params += "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode("" + tvpassword.getText(), "UTF-8");
                params += "&" + URLEncoder.encode("Mobile", "UTF-8") + "=" + URLEncoder.encode("" + tvmobile.getText(), "UTF-8");
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
                Log.d("User Saved or not", stringBuilder.toString());

            } catch (Exception e) {

            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            json_string=result;
           // TextView tv=new TextView(RegisteruserActivity.this);
           // tv.setText(json_string);
           // relativeLayoutt.addView(tv);
              progressDialog.dismiss();
           if(json_string.equals("1"))
            {
                Toast.makeText(RegisteruserActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(RegisteruserActivity.this,LoginuserActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(RegisteruserActivity.this, "This email already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
}
