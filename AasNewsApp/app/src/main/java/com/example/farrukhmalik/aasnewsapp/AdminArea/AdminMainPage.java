package com.example.farrukhmalik.aasnewsapp.AdminArea;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.farrukhmalik.aasnewsapp.R;

public class AdminMainPage extends AppCompatActivity {


    AdminFragmentAdapter cstm;
    ViewPager vp;
    TabLayout tl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);



        cstm = new AdminFragmentAdapter(getSupportFragmentManager());


        vp = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);


        vp.setAdapter(cstm);
        tl.setupWithViewPager(vp);


    }
}
