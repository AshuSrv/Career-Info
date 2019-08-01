package com.example.ashutoshshrivastava.restapi;

public class educationclass {
    geteducation data;
    private String organisation;
    private String degree;
    private String location;
    private String start_year;
    private String end_year;

    public educationclass(String start_year, String degree, String organisation, String location, String end_year) {
        this.organisation = organisation;
        this.degree = degree;
        this.location = location;
        this.start_year = start_year;
        this.end_year = end_year;
    }
}

class geteducation {
    public String organisation;
    public String degree;
    public String location;
    public String start_year;
    public String end_year;

}