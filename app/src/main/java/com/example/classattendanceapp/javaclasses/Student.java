package com.example.classattendanceapp.javaclasses;

public class Student extends User {
    public long en;
    public int sem;
    public String div;
    public String fname, email, password, branch;
    public int userType;

    public Student(int userType, String email, String password){
        this.userType = userType;
        this.email = email;
        this.password = password;
    }

    public Student(long en, String fname, String branch, int sem, String div, String email, String password){
        this.en = en;
        this.fname = fname;
        this.branch = branch;
        this.sem = sem;
        this.div = div;
        this.email = email;
        this.password = password;
    }
}
