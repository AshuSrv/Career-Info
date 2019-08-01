package com.example.ashutoshshrivastava.restapi;

public class ProfessionalDetailClass {
    getprofession data;
    private String organisation;
    private String designation;
    private String start_date;
    private String end_date;

    public ProfessionalDetailClass(String end_date, String organisation ,String designation, String start_date) {
        this.organisation = organisation;
        this.designation = designation;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}

class getprofession {
    public String organisation;
    public String designation;
    public String start_date;
    public String end_date;

}