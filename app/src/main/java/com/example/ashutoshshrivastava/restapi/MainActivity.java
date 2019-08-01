package com.example.ashutoshshrivastava.restapi;

import android.content.Intent;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity {
EditText email, password;
Button login,move;
    Boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.editText2);
        password=findViewById(R.id.editText3);
        login=findViewById(R.id.button);

        move=findViewById(R.id.button2);


        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email.getText())){
                    Toast.makeText(MainActivity.this,"Please Enter Login ID or Your Registered Email",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password.getText()))
                {
                    Toast.makeText(MainActivity.this,"Password Field Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else {

                    String eemail = email.getText().toString();
                    String pass = password.getText().toString();
                    LoginClass loginClass = new LoginClass(eemail, pass);


                    Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl("http://139.59.65.145:9090/")
                            .addConverterFactory(GsonConverterFactory.create());
                    Retrofit retrofit = builder.build();

                    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                    Call<LoginClass> call = loginInterface.login(loginClass);
                    call.enqueue(new Callback<LoginClass>() {
                        @Override
                        public void onResponse(Call<LoginClass> call, Response<LoginClass> response) {
                            String id = response.body().data.id.toString();
                            Intent intent = new Intent(MainActivity.this, HomePage.class);
                            intent.putExtra("key", id);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<LoginClass> call, Throwable t) {

                        }
                    });

                }
            }
        });
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
