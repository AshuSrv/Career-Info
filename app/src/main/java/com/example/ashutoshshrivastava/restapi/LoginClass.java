package com.example.ashutoshshrivastava.restapi;

import android.widget.EditText;

public class LoginClass {
    private String email;
    private String password;
    Data data;

    public Data getData() {
        return data;
    }

    public LoginClass(String s, String s1) {
        this.email=s;
        this.password=s1;
    }
}
class Data{
    public Integer id;
    public String email;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}