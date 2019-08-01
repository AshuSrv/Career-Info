package com.example.ashutoshshrivastava.restapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Filldetails extends AppCompatActivity {
    EditText skills,mobile,name,link,location,email;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filldetails);
        Intent intent=getIntent();
        final String id =intent.getStringExtra("id");
        skills=findViewById(R.id.skills);

        mobile=findViewById(R.id.mobile);
        name=findViewById(R.id.Name);
        link=findViewById(R.id.link);
        location=findViewById(R.id.location);
        email=findViewById(R.id.email);
        submit=findViewById(R.id.submit);






        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(skills.getText()) || TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(mobile.getText()) || TextUtils.isEmpty(link.getText()) || TextUtils.isEmpty(location.getText()) || TextUtils.isEmpty(email.getText())) {

                    Toast.makeText(Filldetails.this, "Some Fields are Blank", Toast.LENGTH_SHORT).show();
                } else {

                    final FillDetailsclass fillDetailsclass = new FillDetailsclass(skills.getText().toString(), mobile.getText().toString(), name.getText().toString(), link.getText().toString(), location.getText().toString(), email.getText().toString());
                    final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://139.59.65.145:9090/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                    loginInterface.filldetails(Integer.parseInt(id), fillDetailsclass).enqueue(new Callback<FillDetailsclass>() {
                        @Override
                        public void onResponse(Call<FillDetailsclass> call, Response<FillDetailsclass> response) {
                            FillDetailsclass fillDetailsclass = response.body();
                            Intent intent = new Intent(Filldetails.this, FillEduDetails.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<FillDetailsclass> call, Throwable t) {

                        }
                    });

                }
            }
        });

    }
}
