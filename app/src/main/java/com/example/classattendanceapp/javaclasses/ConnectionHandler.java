package com.example.classattendanceapp.javaclasses;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.classattendanceapp.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectionHandler {
    private boolean registration;
    private Context contextReference;
    User user;
    HttpURLConnection con;
    PrintStream ps;
    URL url;

    Student student;
    Faculty faculty;
    HOD hod;
    int userType;

    public ConnectionHandler(Student student, boolean registration, Context context){
        this.student = student;
        userType = 1;
        this.registration = registration;
        this.contextReference = context;
        sendData();
    }
    public ConnectionHandler(Faculty faculty, boolean registration, Context context){
        this.faculty = faculty;
        userType = 2;
        this.registration = registration;
        this.contextReference = context;
        sendData();
    }
    public ConnectionHandler(HOD hod, boolean registration, Context context){
        this.hod = hod;
        userType = 3;
        this.registration = registration;
        this.contextReference = context;
        sendData();
    }

    private void sendData(){
        if(registration){
            new RegistrationTask(contextReference).execute();
        }
        else{
            new LoginTask(contextReference).execute();
        }
    }


    class RegistrationTask extends AsyncTask<Void, Void, String>{
        StringBuilder sb;
        String msg;
        private WeakReference<Context> contextReference;
        public RegistrationTask(Context context){
            this.contextReference = new WeakReference<Context>(context);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                url = new URL("http://192.168.1.104:80/classAttendance/registration.php");
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
                    ps.print("&facId="+faculty.facId);
                    ps.print("&fname="+faculty.fname);
                    ps.print("&branch="+faculty.branch);
                    ps.print("&email="+faculty.email);
                    ps.print("&password="+faculty.password);
                }
                else if(userType == 3){
                    ps.print("&hodId="+hod.hodId);
                    ps.print("&fname="+hod.fname);
                    ps.print("&branch="+hod.branch);
                    ps.print("&email="+hod.email);
                    ps.print("&password="+hod.password);
                }
                ps.close();
                int responseCode = con.getResponseCode();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                sb = new StringBuilder();
                String line = null;
                while((line = br.readLine())!=null){
                    sb.append(line);
                    break;
                }
                msg = sb.toString();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String result){
            AppCompatActivity context = (AppCompatActivity) contextReference.get();
            if(context!=null){
                TextView alert = context.findViewById(R.id.resultAlert);
                alert.setText(result);
                alert.setVisibility(View.VISIBLE);
            }
        }
    }

    class LoginTask extends AsyncTask<Void, Void, String>{
        StringBuilder sb;
        String msg;
        private WeakReference<Context> contextReference;
        public LoginTask(Context context){
            this.contextReference = new WeakReference<Context>(context);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                url = new URL("http://192.168.1.104:80/classAttendance/login.php");
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
                String line = null;
                while((line = br.readLine())!=null){
                    sb.append(line);
                    break;
                }
                msg = sb.toString();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String result){
            AppCompatActivity context = (AppCompatActivity) contextReference.get();
            if(context!=null){
                TextView alert = context.findViewById(R.id.resultAlert);
                alert.setText(result);
                alert.setVisibility(View.VISIBLE);
            }
        }

    }
}




