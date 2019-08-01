package com.example.ashutoshshrivastava.restapi;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.Menu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePage extends AppCompatActivity {
    TextView skills,mobile,name,link,location,email,displayName;

    Boolean doubleBackToExitPressedOnce = false;
    Button button;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent intent=getIntent();
        final String id =intent.getStringExtra("key");
        skills=findViewById(R.id.skillls);
        mobile=findViewById(R.id.mobNo);
        name=findViewById(R.id.namee);
        drawerLayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.nav_view);
        link=findViewById(R.id.emailll);
        displayName=findViewById(R.id.DisplayName);
        location=findViewById(R.id.locationn);



        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

              final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://139.59.65.145:9090/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LoginInterface loginInterface=retrofit.create(LoginInterface.class);
                loginInterface.getdetails(Integer.parseInt(id)).enqueue(new Callback<FillDetailsclass>() {
                    @Override
                    public void onResponse(Call<FillDetailsclass> call, Response<FillDetailsclass> response) {
                        FillDetailsclass fillDetailsclass=response.body();
                        skills.setText(fillDetailsclass.data.skills);
                        mobile.setText(fillDetailsclass.data.mobile_no);
                        name.setText(fillDetailsclass.data.name);
                        link.setText(fillDetailsclass.data.links);
                        location.setText(fillDetailsclass.data.location);
                        displayName.setText("Hi " + fillDetailsclass.data.name);
                    }

                    @Override
                    public void onFailure(Call<FillDetailsclass> call, Throwable t) {

                    }
                });






        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.pd:
                        Intent intent=new Intent(HomePage.this,Professionaldetails.class);
                        intent.putExtra("key",id);
                        startActivity(intent);
                        break;

                    case R.id.ed:
                         intent=new Intent(HomePage.this,edudetailview.class);
                       intent.putExtra("key",id);
                        startActivity(intent);
                        break;

                    case R.id.setting:
//                        intent=new Intent(HomePage.this,RegisterdPeopleSection.class);
//                        startActivity(intent);
                        Toast.makeText(HomePage.this,"Not Yet Programmed",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.help:
//                        intent=new Intent(HomePage.this,RegisterdPeopleSection.class);
//                        startActivity(intent);
                        Toast.makeText(HomePage.this,"Not Yet Programmed",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Feedback:
//                        intent=new Intent(HomePage.this,RegisterdPeopleSection.class);
//                        startActivity(intent);
                        Toast.makeText(HomePage.this,"Not Yet Programmed",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logOut:
                        intent=new Intent(HomePage.this,MainActivity.class);
                         startActivity(intent);

                }

                drawerLayout.closeDrawers();
                return true;


            }
        });


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_toolbar, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;


        }
        super.onOptionsItemSelected(item);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            finish();
        } else {

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to Quit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

}
