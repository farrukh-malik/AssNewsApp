package com.example.farrukhmalik.aasnewsapp.UserArea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.farrukhmalik.aasnewsapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomePage extends AppCompatActivity {

    TextView myDate ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        myDate = (TextView) findViewById(R.id.currentdate);

        String myDt = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        myDate.setText(myDt);


    }
}
