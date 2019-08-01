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

public class Professionaldetails extends AppCompatActivity {
    TextView Organisation,designation,start_date,end;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professionaldetails);

        Intent intent=getIntent();
        final String id =intent.getStringExtra("key");
        Organisation=findViewById(R.id.organ);
        designation=findViewById(R.id.despacito);
        start_date=findViewById(R.id.std);
        update=findViewById(R.id.update);
        end=findViewById(R.id.eddddd);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.59.65.145:9090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginInterface loginInterface=retrofit.create(LoginInterface.class);
        loginInterface.getprofessiondetails(Integer.parseInt(id)).enqueue(new Callback<ProfessionalDetailClass>() {
            @Override
            public void onResponse(Call<ProfessionalDetailClass> call, Response<ProfessionalDetailClass> response) {
                final ProfessionalDetailClass professionalDetailClass=response.body();
                Organisation.setText(professionalDetailClass.data.organisation);
                designation.setText(professionalDetailClass.data.designation);
                start_date.setText(professionalDetailClass.data.start_date);
                end.setText(professionalDetailClass.data.end_date);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(Professionaldetails.this,UpdateProfDetail.class);
                        intent1.putExtra("organisation",professionalDetailClass.data.organisation);
                        intent1.putExtra("designation",professionalDetailClass.data.designation);
                        intent1.putExtra("start_date",professionalDetailClass.data.start_date);
                        intent1.putExtra("end",professionalDetailClass.data.end_date);
                        intent1.putExtra("id",id);
                        startActivity(intent1);
                    }
                });
            }

            @Override
            public void onFailure(Call<ProfessionalDetailClass> call, Throwable t) {

            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_toolbar, menu);
        return true;
    }

}
