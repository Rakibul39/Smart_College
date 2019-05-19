package com.example.smartcollege;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPage extends AppCompatActivity {
    private Button button;
    EditText emailTxt, passTxt;
    TextView register;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        emailTxt = (EditText) findViewById(R.id.mail_field);
        passTxt = (EditText) findViewById(R.id.pass_field);
        button = (Button)findViewById(R.id.btn_login);
        register =(TextView)findViewById(R.id.tv_register) ;
        progressBar = findViewById(R.id.progressbar);

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailTxt.getText().toString();
                String password = passTxt.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginPage.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginPage.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(LoginPage.this, "Password is too sort", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    finish();
                                    Toast.makeText(LoginPage.this, "Login Successfull....!", Toast.LENGTH_SHORT).show();
                                    openHomePage();

                                } else {
                                    Toast.makeText(LoginPage.this, "Login failed or user not registered", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }

        });
    }
    public void openHomePage(){
        Intent intent = new Intent(this, Navigation_Drawer.class);
        startActivity(intent);
    }

}
