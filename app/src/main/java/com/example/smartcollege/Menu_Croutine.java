package com.example.smartcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Croutine extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_class_routine);
        button = (Button)findViewById(R.id.btn_CSE);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                slideOpen();
            }

        });
    }

    public void slideOpen(){
        Intent intent = new Intent(this, SlideHelper.class);
        startActivity(intent);
    }
}
