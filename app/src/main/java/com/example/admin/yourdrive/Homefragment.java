package com.example.admin.yourdrive;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.zip.Inflater;

public class Homefragment extends Fragment  {

    View myView;

    Button btnsearchcars;

    public Homefragment()
    {

    }
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.home_layout,container,false);
        btnsearchcars=(Button) myView.findViewById(R.id.btnsearchcars);
        btnsearchcars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchcarsActivity.class);
                getActivity().startActivity(intent);

            }
        });
        return myView;
    }


}
