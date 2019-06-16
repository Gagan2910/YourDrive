package com.example.admin.yourdrive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Aboutusfragment extends Fragment {

    View myView;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.aboutus_layout,container,false);
        tv=(TextView)myView.findViewById(R.id.txtviewabout);
        tv.setText("Founded in 2015 by Oscar Ellison, Yourdrive is New Zealand's first peer-to-peer car sharing service. Our online " +
                "platform enables people to rent cars from locals throughout New Zealand.");
        return myView;
    }
}
