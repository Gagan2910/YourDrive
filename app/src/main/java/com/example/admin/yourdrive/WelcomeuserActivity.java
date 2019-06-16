package com.example.admin.yourdrive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class WelcomeuserActivity extends AppCompatActivity {
    String json_string="";
    TextView tvuser;
Button btnowner,btnprofile,btnlogout;
android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeuser);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
        json_string = getIntent().getExtras().getString("email");
        tvuser=(TextView)findViewById(R.id.txtuser);
        tvuser.setText(json_string);
        btnowner=(Button) findViewById(R.id.contactowner);
        btnowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeuserActivity.this,ContactownerActivity.class);
                startActivity(intent);
            }
        });
        btnprofile=(Button)findViewById(R.id.accountprofile);
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeuserActivity.this,UserprofileActivity.class);
                intent.putExtra("email", json_string);
                startActivity(intent);
            }
        });
        btnlogout=(Button)findViewById(R.id.logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeuserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
