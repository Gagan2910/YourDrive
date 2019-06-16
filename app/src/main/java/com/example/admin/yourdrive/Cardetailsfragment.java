package com.example.admin.yourdrive;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Cardetailsfragment extends Fragment {
    Button btnrentcar;
    View myView;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.cardetails_layout,container,false);
        btnrentcar=(Button)myView.findViewById(R.id.btnrentcar);
        btnrentcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchcarsActivity.class);
                startActivity(intent);

            }
        });
        return myView;
    }
}
