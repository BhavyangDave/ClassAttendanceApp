package com.example.classattendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    Spinner chooseType;
    //int i=1,j=2,k=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        chooseType =(Spinner) findViewById(R.id.loginType);

        chooseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Intent intent = new Intent(RegistrationActivity.this, RegistrationForm.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void toLogin(View view){
        Intent loginIntent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(loginIntent);
    }
}
