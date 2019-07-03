package com.example.smartcollege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Notice_board_add extends AppCompatActivity {

    private EditText noticeTitle,noticeBody;
    private Button btnPublish;
    Notices notices;

    private FirebaseDatabase database;
    private DatabaseReference myref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board_add);

        noticeTitle = findViewById(R.id.noticeTitle);
        noticeBody = findViewById(R.id.noticeBody);
        btnPublish = findViewById(R.id.btn_publish);

        notices = new Notices();

        database = FirebaseDatabase.getInstance();
        myref = database.getReference("Notice").child("abc");

        btnPublish.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String title = noticeTitle.getText().toString();
                final String body = noticeBody.getText().toString();

                if( title.isEmpty() || body.isEmpty()){
                    showMessage("Please Verify all fields") ;
                }

                else{
                    notices.setTitle(title);
                    notices.setBody(body);
                    myref.setValue(notices);
                    showMessage("Notice Published");
                }
            }
        });
    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
}
