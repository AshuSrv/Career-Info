package com.example.ashutoshshrivastava.restapi;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edudetailview extends AppCompatActivity {
    TextView Organisation,Degree,location,startyear,endyear;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edudetailview);

        final Intent intent=getIntent();
        final String id =intent.getStringExtra("key");
        Organisation=findViewById(R.id.org);
        location=findViewById(R.id.loc);
        Degree=findViewById(R.id.deg);
        startyear=findViewById(R.id.sd);
        update=findViewById(R.id.update);
        endyear=findViewById(R.id.endd);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.59.65.145:9090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginInterface loginInterface=retrofit.create(LoginInterface.class);
        loginInterface.geteducationdetails(Integer.parseInt(id)).enqueue(new Callback<educationclass>() {
            @Override
            public void onResponse(Call<educationclass> call, Response<educationclass> response) {
                final educationclass educationclasss=response.body();

                Organisation.setText(educationclasss.data.organisation);
                location.setText(educationclasss.data.location);
                Degree.setText(educationclasss.data.degree);
                startyear.setText(educationclasss.data.start_year);
                endyear.setText(educationclasss.data.end_year);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(edudetailview.this,UpdateEduDetail.class);
                        intent1.putExtra("organisation",educationclasss.data.organisation);
                        intent1.putExtra("location",educationclasss.data.location);
                        intent1.putExtra("Degree",educationclasss.data.degree);
                        intent1.putExtra("startyear",educationclasss.data.start_year);
                        intent1.putExtra("endyear",educationclasss.data.end_year);
                        intent1.putExtra("id",id);
                        startActivity(intent1);
                    }
                });


            }


            @Override
            public void onFailure(Call<educationclass> call, Throwable t) {

            }
        });






    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_toolbar, menu);
        return true;
    }

}
