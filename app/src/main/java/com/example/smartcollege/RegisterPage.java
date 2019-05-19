package com.example.smartcollege;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterPage extends AppCompatActivity {

    private EditText userEmail,userPassword,userPAssword2,userName;
    private ProgressBar loadingProgress;
    private Button regBtn;
    private ProgressDialog mProgress;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        //ini views
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPAssword2 = findViewById(R.id.regPassword2);
        userName = findViewById(R.id.regName);
        loadingProgress = findViewById(R.id.regProgressBar);
        regBtn = findViewById(R.id.regBtn);
        mProgress = new ProgressDialog(this);
        loadingProgress.setVisibility(View.INVISIBLE);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference().child("Users");

        //myRef.setValue("Hello, firebase");


        mAuth = FirebaseAuth.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPAssword2.getText().toString();
                final String name = userName.getText().toString();

                if( email.isEmpty() || name.isEmpty() || password.isEmpty()  || !password.equals(password2)) {


                    // something goes wrong : all fields must be filled
                    // we need to display an error message
                    showMessage("Please Verify all fields") ;
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);


                }
                if(password.length()<6){
                    Toast.makeText(RegisterPage.this, "Password is too sort. Need minimum 6 digit", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    // everything is ok and all fields are filled now we can start creating user account
                    // CreateUserAccount method will try to create the user if the email is valid
                    mProgress.setMessage("Signing Up....");
                    mProgress.show();

                    CreateUserAccount(email,name,password);
                }


            }
        });


    }

    private void CreateUserAccount(final String email, final String name, String password) {


        // this method create user account with specific email and password

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // user account created successfully
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user = mDatabase.child(user_id);
                            current_user.child("name").setValue(name);
                            current_user.child("email").setValue(email);

                            mProgress.dismiss();
                            showMessage("Account created");
                            Intent loginIntent = new Intent(RegisterPage.this, LoginPage.class);
                            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(loginIntent);


                        }
                        else
                        {

                            // account creation failed
                            showMessage("account creation failed" + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);

                        }
                    }
                });








    }


    // simple method to show toast message
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();

    }

}
