package com.example.classattendanceapp.javaclasses;

public class Faculty extends User {
    public String facId;
    public String fname, email, password, branch;
    public int userType;

    public Faculty(int userType, String email, String password){
        this.userType = userType;
        this.email = email;
        this.password = password;
    }

    public Faculty(String facId, String fname, String branch, String email, String password){
        this.facId = facId;
        this.fname = fname;
        this.branch = branch;
        this.email = email;
        this.password = password;

    }
}
