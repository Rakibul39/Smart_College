package com.example.smartcollege;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 3000; //This is 3 seconds
    TextView tv;
    ImageView iv;
    Animation myAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        tv = (TextView) findViewById(R.id.tv_wpage);
        iv = (ImageView) findViewById(R.id.iv_user);
        myAnim = (Animation) AnimationUtils.loadAnimation(this,R.anim.anim) ;
        tv.startAnimation(myAnim);
        iv.startAnimation(myAnim);

        flashScreen();
    }

    public void flashScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(MainActivity.this, Navigation_Drawer.class);
                startActivity(mySuperIntent);
                /* This 'finish()' is for exiting the app when back button pressed
                 *  from Home page which is ActivityHome
                 */
                finish();
            }
        }, SPLASH_TIME);
    }
}
