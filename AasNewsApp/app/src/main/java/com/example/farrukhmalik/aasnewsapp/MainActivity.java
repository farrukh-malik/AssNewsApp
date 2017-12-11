package com.example.farrukhmalik.aasnewsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farrukhmalik.aasnewsapp.Authentication.LoginPage;
import com.example.farrukhmalik.aasnewsapp.Authentication.SigninPage;
import com.example.farrukhmalik.aasnewsapp.Authentication.SignupPage;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button go;

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        thread = new Thread(){
            @Override
            public void run() {
                try {
                    thread.sleep(3000);
                }catch (Exception ex){
                    ex.printStackTrace();
                } finally {
                    Intent intent = new Intent(MainActivity.this, LoginPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
//        go = (Button) findViewById(R.id.go);
//        go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                startActivity(new Intent(MainActivity.this, LoginPage.class));
//
//            }
//        });

    }
}
