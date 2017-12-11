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
import android.widget.Toast;

import com.example.farrukhmalik.aasnewsapp.AdminArea.AdminMainPage;
import com.example.farrukhmalik.aasnewsapp.MyCustomClass.UserPersonalRecords;
import com.example.farrukhmalik.aasnewsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignupPage extends AppCompatActivity {


    Button signupBtn;
    EditText email, password;
    String stringEmail, stringPassword;
    //////////
    String stringUid;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    FirebaseDatabase db;
    DatabaseReference ref;

    //////////
    UserPersonalRecords userPersonalRecords;
    ArrayList<UserPersonalRecords> userPersonalRecordsArrayList;
///////////////

    EditText firstNameEt, lastNameEt;

    String stringFirstName, stringLastName;

    ///////////



    /////////////
    ProgressDialog progressDialog;
    /////////////




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        /////////INSERT/////////

//        db = FirebaseDatabase.getInstance().;
//        ref = db.getReference().child("user");

        userPersonalRecordsArrayList = new ArrayList<>();


        firstNameEt = (EditText) findViewById(R.id.etUserFirstNameId);
        lastNameEt = (EditText) findViewById(R.id.etUserLastNameId);


        ///////////INSERT////////



        /////////
        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
//
//        if (firebaseAuth.getCurrentUser() == null){
//
//            // startActivity(new Intent(this, Login.class));
//        }else {
//            stringUid = firebaseUser.getUid();//
//
//        }

        ///////////








        ////////
        progressDialog = new ProgressDialog(this);
        ///////////





        email = (EditText) findViewById(R.id.idEmailSs);
        password = (EditText) findViewById(R.id.idPasswordSs);




        signupBtn = (Button) findViewById(R.id.idSignupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                ////////signup ka kaam///
                stringEmail = email.getText().toString().trim();
                stringPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(stringEmail)){
                    // email is empty
                    Toast.makeText(SignupPage.this, "Email is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(stringPassword)){
                    // password is empty
                    Toast.makeText(SignupPage.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    //stoppin the finction executting further
                    return;
                }



///////////////////////
                progressDialog.setMessage("registering user...");
                progressDialog.show();
                /////////////////////////



                firebaseAuth.createUserWithEmailAndPassword(stringEmail,stringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            /////////
                            //finish();
                            ///////////insert/////////

                            stringFirstName = firstNameEt.getText().toString().trim();
                            stringLastName = lastNameEt.getText().toString().trim();



                            userPersonalRecords = new UserPersonalRecords("data", firebaseAuth.getCurrentUser().getUid(), stringFirstName, stringLastName,stringEmail);
                            // ref.child(personalRecord.getKey()).child(stringUid).setValue(personalRecord);

                            FirebaseDatabase.getInstance().getReference().child("users").child(userPersonalRecords.getUid()).setValue(userPersonalRecords);

                            firstNameEt.setText("");
                            lastNameEt.setText("");


                            ///////////insert/////////
                            /////////
                            Toast.makeText(SignupPage.this, "successfully created", Toast.LENGTH_SHORT).show();
                            /////////
                            progressDialog.hide();
                            ////////////
                            startActivity(new Intent(SignupPage.this,AdminMainPage.class));
                        }
                        else {
                            Toast.makeText(SignupPage.this, "Not created! Error", Toast.LENGTH_SHORT).show();
                            /////////
                            progressDialog.hide();
                            ////////////
                        }

                        ////////signup ka kaam/////////////

                    }
                });
            }
        });
    }
}


