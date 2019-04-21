package com.example.classattendanceapp;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    Spinner chooseType;
    Button b;
    boolean registration = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        hideAll();

        b = (Button)findViewById(R.id.button);
        chooseType =(Spinner) findViewById(R.id.userType);
        chooseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0 && registration){
                    createRegistrationForm(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(registration){
                    findViewById(R.id.logo).setVisibility(VISIBLE);
                }
            }
        });

        createLoginForm();
    }

    public void submit(View view){
        if(registration){
            //go for reg
        }
        else{
            //go for login
        }
    }

    public void toRegistration(View view){
        registration = true;
        hideAll();
        chooseType.setSelection(0);
        showRegistration();
    }

    public void toLogin(View view){
        registration = false;
        hideAll();
        chooseType.setSelection(0);
        createLoginForm();
    }


    protected void showRegistration() {
        findViewById(R.id.logo).setVisibility(VISIBLE);
        findViewById(R.id.spaceBetween).setVisibility(VISIBLE);
        findViewById(R.id.registrationTitle).setVisibility(VISIBLE);

        chooseType.setVisibility(VISIBLE);

        findViewById(R.id.loginLabel).setVisibility(VISIBLE);
        findViewById(R.id.loginLink).setVisibility(VISIBLE);
    }

    protected void createRegistrationForm(int position){
        EditText name = (EditText)findViewById(R.id.name);
        EditText id = (EditText)findViewById(R.id.facId);

        findViewById(R.id.logo).setVisibility(GONE);
        findViewById(R.id.name).setVisibility(VISIBLE);
        findViewById(R.id.cpassword).setVisibility(VISIBLE);
        findViewById(R.id.loginLink).setVisibility(VISIBLE);
        findViewById(R.id.email).setVisibility(VISIBLE);
        findViewById(R.id.password).setVisibility(VISIBLE);

         b.setVisibility(VISIBLE);
         b.setText("Register");

        if(position == 1){
            name.setHint("Student Name");
            findViewById(R.id.en).setVisibility(VISIBLE);
            findViewById(R.id.sem).setVisibility(VISIBLE);
            findViewById(R.id.div).setVisibility(VISIBLE);
            findViewById(R.id.facId).setVisibility(GONE);
            chooseType.setSelection(1);
        }
        else if(position == 2){
            name.setHint("Faculty Name");
            id.setHint("Faculty ID");
            findViewById(R.id.facId).setVisibility(VISIBLE);
            chooseType.setSelection(2);
            findViewById(R.id.en).setVisibility(GONE);
            findViewById(R.id.sem).setVisibility(GONE);
            findViewById(R.id.div).setVisibility(GONE);
        }else if(position == 3){
            name.setHint("Head of Department Name");
            id.setHint("Head of Department ID");
            findViewById(R.id.facId).setVisibility(VISIBLE);
            chooseType.setSelection(3);
            findViewById(R.id.en).setVisibility(GONE);
            findViewById(R.id.sem).setVisibility(GONE);
            findViewById(R.id.div).setVisibility(GONE);
        }
    }

    private void createLoginForm(){
        //LOGO visible
        findViewById(R.id.logo).setVisibility(VISIBLE);

        findViewById(R.id.loginTitle).setVisibility(VISIBLE);
        findViewById(R.id.spaceBetween).setVisibility(VISIBLE);

        //Spinner visible
        chooseType.setVisibility(VISIBLE);

        //email and password visible
        findViewById(R.id.email).setVisibility(VISIBLE);
        findViewById(R.id.password).setVisibility(VISIBLE);

        //button visible and text change
        b.setText("Log in");
        b.setVisibility(VISIBLE);

        //registration link and label visible
        findViewById(R.id.registrationLabel).setVisibility(VISIBLE);
        findViewById(R.id.registrationLink).setVisibility(VISIBLE);
    }


    private void hideAll(){
        findViewById(R.id.logo).setVisibility(GONE);
        findViewById(R.id.userType).setVisibility(GONE);
        findViewById(R.id.password).setVisibility(GONE);
        findViewById(R.id.registrationLink).setVisibility(GONE);
        findViewById(R.id.registrationLabel).setVisibility(GONE);
        findViewById(R.id.email).setVisibility(GONE);
        findViewById(R.id.loginLink).setVisibility(GONE);
        findViewById(R.id.loginTitle).setVisibility(GONE);
        findViewById(R.id.name).setVisibility(GONE);
        findViewById(R.id.cpassword).setVisibility(GONE);
        findViewById(R.id.sem).setVisibility(GONE);
        findViewById(R.id.div).setVisibility(GONE);
        findViewById(R.id.facId).setVisibility(GONE);
        findViewById(R.id.spaceBetween).setVisibility(GONE);
        findViewById(R.id.button).setVisibility(GONE);
        findViewById(R.id.en).setVisibility(GONE);
        findViewById(R.id.registrationTitle).setVisibility(GONE);
        findViewById(R.id.loginLabel).setVisibility(GONE);
    }
}