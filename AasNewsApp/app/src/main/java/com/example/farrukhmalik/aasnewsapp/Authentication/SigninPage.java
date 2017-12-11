package com.example.farrukhmalik.aasnewsapp.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farrukhmalik.aasnewsapp.AdminArea.AdminMainPage;
import com.example.farrukhmalik.aasnewsapp.R;
import com.example.farrukhmalik.aasnewsapp.StaticVariables;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninPage extends AppCompatActivity {


    //////auth////////
    EditText email, password;

    String stringEmail, stringPassword;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    /////////////////////

    /////////////
    ProgressDialog progressDialog;
    /////////////


    Button signinBtn;

    TextView signupTxt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);




        ////////
        progressDialog = new ProgressDialog(this);
        ///////////

        /////////////////////
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

//        if (firebaseAuth.getCurrentUser() != null){
//         StaticVariables.uid = firebaseAuth.getCurrentUser().getUid();
//          startActivity(new Intent(SigninPage.this, SignupPage.class));
//        }else {
//            Toast.makeText(this, "Login plzz", Toast.LENGTH_SHORT).show();
//        }

        email = (EditText) findViewById(R.id.idEmailS);
        password = (EditText) findViewById(R.id.idPasswordS);

        signinBtn = (Button) findViewById(R.id.idsigninBtn);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringEmail = email.getText().toString().trim();
                stringPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(stringEmail)){
                    // email is empty
                    Toast.makeText(SigninPage.this, "Email is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(stringPassword)){
                    // password is empty
                    Toast.makeText(SigninPage.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    //stoppin the finction executting further
                    return;
                }




///////////////////////
                progressDialog.setMessage("Wait...");
                progressDialog.show();
                /////////////////////////

                firebaseAuth.signInWithEmailAndPassword(stringEmail,stringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            /////////
                            //finish();
                            /////////
                            Toast.makeText(SigninPage.this, "Successfully Signin", Toast.LENGTH_SHORT).show();
                            /////////
                            progressDialog.hide();
                            ////////////

                            StaticVariables.uid = firebaseAuth.getCurrentUser().getUid();
                            startActivity(new Intent(SigninPage.this,AdminMainPage.class));
                        }else {
                            Toast.makeText(SigninPage.this, "Not successful! Error", Toast.LENGTH_SHORT).show();
                            /////////
                            progressDialog.hide();
                            ////////////
                        }


                    }
                });
            }
        });


        ////////////////////////



//        signupTxt = (TextView) findViewById(R.id.idtxtSignup);
//        signupTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(SigninPage.this, SignupPage.class));
//            }
//        });







    }



    //splash work
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        System.exit(0);

    }
}
