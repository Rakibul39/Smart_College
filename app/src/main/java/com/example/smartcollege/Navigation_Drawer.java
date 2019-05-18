package com.example.smartcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Navigation_Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private Button button,button2,button3,button4;
        FirebaseAuth mAuth;
        FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__drawer);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.tec_logo);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        button = (Button)findViewById(R.id.btn_edFiles);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMenu();
            }

        });

        /*editButton = (Button)findViewById(R.id.btn_Edit);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openEditProfile();
            }

        });*/

        button2 = (Button)findViewById(R.id.btn_gnNotice);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMenu2();
            }

        });

        button3 = (Button)findViewById(R.id.btn_fcInfo);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMenu3();
            }

        });

        button4 = (Button)findViewById(R.id.btn_routine);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMenu4();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hi! Do you need any help?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //updateNavHeader();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation__drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logOut) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_editProfile) {
            Intent intent = new Intent(this, EditProfilePage.class);
            startActivity(intent);

        } else if (id == R.id.nav_file) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openMenu(){
        Intent intent = new Intent(this, Menu_EduFile.class);
        startActivity(intent);
    }

    public void openMenu2(){
        Intent intent = new Intent(this, Menu_Gnotice.class);
        startActivity(intent);
    }
    public void openMenu3(){
        Intent intent = new Intent(this, SlideHelper.class);
        startActivity(intent);
    }

    public void openMenu4(){
        Intent intent = new Intent(this, Menu_Croutine.class);
        startActivity(intent);
    }

    public void updateNavHeader(){
        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        //Button editButton = headerView.findViewById(R.id.btn_Edit);
        ImageView navUserPhot = headerView.findViewById(R.id.imv_navHeader);
        EditText navUsername = headerView.findViewById(R.id.tv_uname);
        EditText navUserMail = headerView.findViewById(R.id.tv_email);

        navUserMail.setText("rony@gmail.com");
        navUsername.setText("Rakibul Rony");

        // now we will use Glide to load user image
        // first we need to import the library

        //Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhot);

        /*editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openEditProfile();
            }

        });*/
    }

    /*public void openEditProfile(){
        Intent intent = new Intent(this, EditProfilePage.class);
        startActivity(intent);
    }*/
}
