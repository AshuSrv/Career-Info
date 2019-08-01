package com.example.ashutoshshrivastava.restapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FillProfDetail extends AppCompatActivity {
    EditText skills,mobile,name,link;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_prof_detail);


        Intent intent=getIntent();
        final String id =intent.getStringExtra("id");
        skills=findViewById(R.id.skills);
        mobile=findViewById(R.id.mobile);
        name=findViewById(R.id.Name);
        link=findViewById(R.id.link);
        submit=findViewById(R.id.submit);



        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(skills.getText()) || TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(mobile.getText()) || TextUtils.isEmpty(link.getText())) {

                    Toast.makeText(FillProfDetail.this, "Some Fields are Blank", Toast.LENGTH_SHORT).show();
                } else {

                    final ProfessionalDetailClass professionalDetailClass = new ProfessionalDetailClass(skills.getText().toString(), mobile.getText().toString(), name.getText().toString(), link.getText().toString());
                    final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://139.59.65.145:9090/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                    loginInterface.fillprofdetail(Integer.parseInt(id), professionalDetailClass).enqueue(new Callback<ProfessionalDetailClass>() {
                        @Override
                        public void onResponse(Call<ProfessionalDetailClass> call, Response<ProfessionalDetailClass> response) {
                            ProfessionalDetailClass professionalDetailClass1 = response.body();
                            Intent intent = new Intent(FillProfDetail.this, MainActivity.class);
                            Toast.makeText(FillProfDetail.this, "Sign Up Successfull", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ProfessionalDetailClass> call, Throwable t) {
                            Toast.makeText(FillProfDetail.this, "Something Wrong", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }
}
