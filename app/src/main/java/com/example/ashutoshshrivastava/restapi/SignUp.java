package com.example.ashutoshshrivastava.restapi;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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

public class SignUp extends AppCompatActivity {
EditText email,password,retypepassword;
Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.emailsignup);
        password=findViewById(R.id.passwordsignup);
       signup=findViewById(R.id.signup);


       retypepassword=findViewById(R.id.retypedpassord);

        signup.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email.getText())){
                    Toast.makeText(SignUp.this,"Please Enter Login ID or Your Email",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password.getText()))
                {
                    Toast.makeText(SignUp.this,"Password Field Cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().compareTo(retypepassword.getText().toString())!=0)
                {
                    Snackbar snackbar = Snackbar
                            .make(v, "Password is not same", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {

                    String eemail = email.getText().toString();
                    String passwordd = password.getText().toString();

                    final LoginClass loginClass = new LoginClass(eemail, passwordd);
                    final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://139.59.65.145:9090/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LoginInterface loginInterface = retrofit.create(LoginInterface.class);
                    loginInterface.signup(loginClass).enqueue(new Callback<LoginClass>() {
                        @Override
                        public void onResponse(Call<LoginClass> call, Response<LoginClass> response) {
                            LoginClass loginClass = response.body();
                            String id = String.valueOf(loginClass.data.id);
                            Intent intent = new Intent(SignUp.this, Filldetails.class);
                            intent.putExtra("id", id);
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
}
