package com.example.admin.yourdrive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class CardescriptionActivity extends AppCompatActivity {
TextView tvcarmd,tvdesc,tvloc,tvpick,tvdropff,tvtripcost;
android.support.v7.widget.Toolbar toolbar;
ImageView carimg;
Button btnbook;
    private static final String TAG = "Cardescription Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardescription);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Drive");
        btnbook=(Button)findViewById(R.id.btnbookcar) ;
        getincomingIntenet();
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CardescriptionActivity.this,LoginuserActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getincomingIntenet()
    {
        Log.d(TAG,"getIncomingIntent: checking for incoming intent");
        if(getIntent().hasExtra("CarModel")&& getIntent().hasExtra("CarImage")&&
                getIntent().hasExtra("Cardesc")&&getIntent().hasExtra("Carloc")
                &&getIntent().hasExtra("Carpickup")&&getIntent().hasExtra("Cardropoff")
                &&getIntent().hasExtra("Cartripcost"))
        {
            Log.d(TAG,"getIncomingIntent: found intent extras.");
           String carmodel=getIntent().getStringExtra("CarModel");
            String carimage=getIntent().getStringExtra("CarImage");
            String cardes=getIntent().getStringExtra("Cardesc");
            String carloc=getIntent().getStringExtra("Carloc");
            String carpickup=getIntent().getStringExtra("Carpickup");
            String cardropoff=getIntent().getStringExtra("Cardropoff");
            String cartripcost=getIntent().getStringExtra("Cartripcost");
            setdata(carmodel,carimage,cardes,carloc,carpickup,cardropoff,cartripcost);

        }
    }
    private void setdata(String carmodel, String carimage,String cardesc,String carloc,String carpickup,String cardropoff,String cartripcost)
    {
        Log.d(TAG,"Set car model to widget");
        tvcarmd=(TextView)findViewById(R.id.txtcarmodel);
        tvcarmd.setText(carmodel);

        carimg=(ImageView)findViewById(R.id.imgcarmodel);
        Picasso.with(this).load(carimage).into(carimg);

        tvdesc=(TextView)findViewById(R.id.txtcardesc);
        tvdesc.setText(cardesc);

        tvloc=(TextView)findViewById(R.id.txtloc);
         tvloc.setText(carloc);

        tvpick=(TextView)findViewById(R.id.txtpickupdate) ;
        tvpick.setText(carpickup);

        tvdropff=(TextView)findViewById(R.id.txtdropoffdate);
        tvdropff.setText(cardropoff);

        tvtripcost=(TextView)findViewById(R.id.txttripcost);
        tvtripcost.setText(cartripcost);
    }

}
