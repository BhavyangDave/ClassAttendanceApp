package com.example.classattendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class RegistrationForm extends AppCompatActivity {

    Spinner chooseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        chooseType =(Spinner) findViewById(R.id.loginType);

        chooseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long i){
                //Intent intent = getIntent();
                //int position = intent.getIntExtra("position", 0);
                createForm(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView){

            }
        });
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        createForm(position);
    }

    public void createForm(int position){
        EditText name = (EditText)findViewById(R.id.name);
        EditText id = (EditText)findViewById(R.id.facId);
        Spinner chooseType = (Spinner)findViewById(R.id.loginType);
        if(position == 1){
            name.setHint("Student Name");
            findViewById(R.id.en).setVisibility(VISIBLE);
            findViewById(R.id.sem).setVisibility(VISIBLE);
            findViewById(R.id.div).setVisibility(VISIBLE);
            findViewById(R.id.facId).setVisibility(View.GONE);
            chooseType.setSelection(1);
        }
        else if(position == 2){
            name.setHint("Faculty Name");
            id.setHint("Faculty ID");
            findViewById(R.id.facId).setVisibility(VISIBLE);
            chooseType.setSelection(2);
            findViewById(R.id.en).setVisibility(View.GONE);
            findViewById(R.id.sem).setVisibility(View.GONE);
            findViewById(R.id.div).setVisibility(View.GONE);
        }else if(position == 3){
            name.setHint("Head of Department Name");
            id.setHint("Head of Department ID");
            findViewById(R.id.facId).setVisibility(VISIBLE);
            chooseType.setSelection(3);
            findViewById(R.id.en).setVisibility(View.GONE);
            findViewById(R.id.sem).setVisibility(View.GONE);
            findViewById(R.id.div).setVisibility(View.GONE);
        }
    }

    public void toLogin(View view){
        Intent loginIntent = new Intent(RegistrationForm.this, MainActivity.class);
        startActivity(loginIntent);
    }
}
