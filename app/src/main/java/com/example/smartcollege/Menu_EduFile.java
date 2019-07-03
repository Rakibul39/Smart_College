package com.example.smartcollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_EduFile extends AppCompatActivity {

    private Button classNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__edu_file);

        classNote = (Button)findViewById(R.id.btn_classNotes);
        classNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMenu();
            }

        });
    }

    public void openMenu(){
        Intent intent = new Intent(this, View_PDF_Files.class);
        startActivity(intent);
    }
}
