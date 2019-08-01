package com.example.ashutoshshrivastava.restapi;

public class FillDetailsclass {
     GetUserDetail data;
    private String skills;
    private String mobile_no;
    private String name;
    private String links;
    private String location;
    private String email;

    public FillDetailsclass(String skills, String mobile_no, String name, String links, String location, String email) {
        this.skills = skills;
        this.mobile_no = mobile_no;
        this.name = name;
        this.links = links;
        this.location = location;
        this.email = email;
    }
}

class GetUserDetail {
    public String skills;
    public Object image;
    public String uid;
    public String mobile_no;
    public String name;
    public String links;
    public String location;
    public String id;
}