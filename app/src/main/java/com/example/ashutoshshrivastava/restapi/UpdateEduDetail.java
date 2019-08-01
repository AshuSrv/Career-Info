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

public class UpdateEduDetail extends AppCompatActivity {

    EditText skills, mobile, name, link, location;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_edu_detail);
        Intent intent=getIntent();
//        String organisation=intent.getStringExtra("organisation");
//        String degree=intent.getStringExtra("Degree");
//        String startyear=intent.getStringExtra("startyear");
//        String locationn=intent.getStringExtra("location");
//        String endyear=intent.getStringExtra("endyear");
        final String id=intent.getStringExtra("id");


        skills = findViewById(R.id.skills);

        mobile = findViewById(R.id.mobile);
        name = findViewById(R.id.Name);
        link = findViewById(R.id.link);
        location = findViewById(R.id.location);
        submit = findViewById(R.id.submit);

//
//        skills.setText(startyear);
//        mobile.setText(degree);
//        name.setText(organisation);
//        link.setText(locationn);
//        location.setText(endyear);
//
//



        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(skills.getText()) || TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(mobile.getText()) || TextUtils.isEmpty(link.getText()) || TextUtils.isEmpty(location.getText())) {

                    Toast.makeText(UpdateEduDetail.this, "Some Fields are Blank", Toast.LENGTH_SHORT).show();
                } else {

                    final educationclass educationclasss = new educationclass(skills.getText().toString(), mobile.getText().toString(), name.getText().toString(), link.getText().toString(), location.getText().toString());
                    final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://139.59.65.145:9090/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                    loginInterface.Updatefilledu(Integer.parseInt(id), educationclasss).enqueue(new Callback<educationclass>() {
                        @Override
                        public void onResponse(Call<educationclass> call, Response<educationclass> response) {
                            educationclass educationclasss = response.body();
                            Toast.makeText(UpdateEduDetail.this, "Update Education details Successfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UpdateEduDetail.this, edudetailview.class);
                            intent.putExtra("key", id);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<educationclass> call, Throwable t) {

                            Toast.makeText(UpdateEduDetail.this, "Update Education details Failed", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });


    }
}
