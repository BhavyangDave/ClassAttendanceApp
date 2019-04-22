package com.example.classattendanceapp.javaclasses;

public class HOD extends User {
    public String hodId;
    public String fname, email, password, branch;
    public int userType;

    public HOD(int userType, String email, String password){
        this.userType = userType;
        this.email = email;
        this.password = password;
    }
    public HOD(String hodID, String fname, String branch, String email, String password){
        this.hodId = hodID;
        this.fname = fname;
        this.branch = branch;
        this.email = email;
        this.password = password;
    }
}
