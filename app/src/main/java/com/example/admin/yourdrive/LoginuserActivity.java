package com.example.admin.yourdrive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class LoginuserActivity extends AppCompatActivity {
    private static final String TAG ="LoginuserActivity" ;
    Button btnlogin,btnregister;
TextView txtemail,txtpassword;
android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
        txtemail=(TextView)findViewById(R.id.Useremail);
        txtpassword=(TextView)findViewById(R.id.Userpassword);
        btnregister=(Button)findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginuserActivity.this,RegisteruserActivity.class);
                startActivity(intent);
            }
        });
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtemail.getText().toString();
                if (txtemail.length() == 0) {
                    txtemail.requestFocus();
                    txtemail.setError("Field cannot be empty");
                    //Toast.makeText(AddmemoActivity.this,"Please enter the values",Toast.LENGTH_LONG).show();
                } else if (txtpassword.length() == 0) {
                    txtpassword.requestFocus();
                    txtpassword.setError("Field cannot be empty");
                }
                else if (!isEmailValid(email)) {
                    txtemail.setError("This email address is invalid");
                }
                else  {
                    new login().execute();
                }

            }

            private boolean isEmailValid(String email) {
                return email.contains("@");
            }
        });
    }
    class login extends AsyncTask<Void,Void,String>
    {
            String json_string;
            String useremail="";
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(LoginuserActivity.this);
            progressDialog.setMessage("Loading Data..");
            progressDialog.show();
        }

        @Override
            protected String doInBackground(Void... voids) {
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    //Step1:Create Url object and open connection
                    URL url = new URL(AppSettings.URL_ADDRESS + "loginuser.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //Step2:Create parameters to pass
                    String params = URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode("" + txtemail.getText(), "UTF-8");
                    params += "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode("" + txtpassword.getText(), "UTF-8");
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
                    Log.d("User found or not", stringBuilder.toString());

                } catch (Exception e) {

                }

                return stringBuilder.toString();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                json_string=result;
                useremail=txtemail.getText().toString();
               // TextView tv=new TextView(LoginuserActivity.this);
                //tv.setText(json_string);
                //relativeLayoutt.addView(tv);
                Log.d(TAG,json_string);
                progressDialog.dismiss();

                if(json_string.equals("1"))
                {
                    Toast.makeText(LoginuserActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginuserActivity.this,WelcomeuserActivity.class);
                    intent.putExtra("email", useremail);
                    startActivity(intent);
                }
                else if(json_string.equals("0"))
                {
                    Toast.makeText(LoginuserActivity.this, "This email doesn't exists.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginuserActivity.this, "Incorrect email or password", Toast.LENGTH_LONG).show();
                }
            }
        }



}
