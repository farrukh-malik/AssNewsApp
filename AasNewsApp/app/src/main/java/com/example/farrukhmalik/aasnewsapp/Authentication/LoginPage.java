package com.example.farrukhmalik.aasnewsapp.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farrukhmalik.aasnewsapp.R;
import com.example.farrukhmalik.aasnewsapp.UserArea.HomePage;

public class LoginPage extends AppCompatActivity {


    Button signinBtn;
    Button signupBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        signupBtn = (Button) findViewById(R.id.btnSignUp);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginPage.this,SignupPage.class));
            }
        });



        signinBtn = (Button) findViewById(R.id.btnSingIn);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginPage.this,SigninPage.class));
            }
        });
    }
}
