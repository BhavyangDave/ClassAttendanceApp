package com.example.classattendanceapp.javaclasses;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectionHandler {
    private boolean registration;
    User user;
    StringBuilder sb;
    public String msg;
    HttpURLConnection con;
    PrintStream ps;
    URL url;

    Student student;
    Faculty faculty;
    HOD hod;
    int userType;

    public ConnectionHandler(Student student, boolean registration){
        this.student = student;
        userType = 1;
        this.registration = registration;
        sendData();
    }
    public ConnectionHandler(Faculty faculty, boolean registration){
        this.faculty = faculty;
        userType = 2;
        this.registration = registration;
        sendData();
    }
    public ConnectionHandler(HOD hod, boolean registration){
        this.hod = hod;
        userType = 3;
        this.registration = registration;
        sendData();
    }

    private void sendData(){
        if(registration){
            new RegistrationTask().execute();
        }
        else{
            new LoginTask().execute();
        }
    }


    class RegistrationTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
                url = new URL("http://192.168.1.104:80/classAttendanceApp/registration.php");
                con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.connect();
                ps = new PrintStream(con.getOutputStream());
                ps.print("&userType="+userType);
                if(userType == 1){
                    ps.print("&en="+student.en);
                    ps.print("&fname="+student.fname);
                    ps.print("&branch="+student.branch);
                    ps.print("&sem="+student.sem);
                    ps.print("&div="+student.div);
                    ps.print("&email="+student.email);
                    ps.print("&password="+student.password);
                }
                else if(userType == 2){
                    ps.print("&facid="+faculty.facId);
                    ps.print("&fname="+faculty.fname);
                    ps.print("&branch="+faculty.branch);
                    ps.print("&email="+faculty.email);
                    ps.print("&password="+faculty.password);
                }
                else if(userType == 3){
                    ps.print("&hodid="+hod.hodId);
                    ps.print("&fname="+hod.fname);
                    ps.print("&branch="+hod.branch);
                    ps.print("&email="+hod.email);
                    ps.print("&password="+hod.password);
                }
                ps.close();
                int responseCode = con.getResponseCode();

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                sb = new StringBuilder();
                while((msg = br.readLine())!=null){
                    sb.append(msg);
                    break;
                }
                msg = sb.toString();
                String line = null;
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return msg;
        }
    }

    class LoginTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
                url = new URL("http://192.168.1.104:80/classAttendanceApp/login.php");
                con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.connect();
                ps = new PrintStream(con.getOutputStream());
                ps.print("&userType="+userType);
                if(userType == 1){
                    ps.print("&email="+student.email);
                    ps.print("&password="+student.password);
                }
                else if(userType == 2){
                    ps.print("&email="+faculty.email);
                    ps.print("&password="+faculty.password);
                }
                else if(userType == 3){
                    ps.print("&email="+hod.email);
                    ps.print("&password="+hod.password);
                }
                ps.close();
                int responseCode = con.getResponseCode();

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                sb = new StringBuilder();
                while((msg = br.readLine())!=null){
                    sb.append(msg);
                    break;
                }
                msg = sb.toString();
                String line = null;
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}




