package com.example.smartcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        button = (Button)findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity2();
            }

        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, Navigation_Drawer.class);
        startActivity(intent);
    }

}
