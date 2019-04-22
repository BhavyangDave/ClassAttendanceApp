package com.example.classattendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.classattendanceapp.javaclasses.ConnectionHandler;
import com.example.classattendanceapp.javaclasses.Faculty;
import com.example.classattendanceapp.javaclasses.HOD;
import com.example.classattendanceapp.javaclasses.Student;
import com.example.classattendanceapp.javaclasses.User;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    Spinner userType, branch, sem, div;
    EditText email, password, fname, en, facId;
    TextView result;
    Button b;
    boolean registration = false;
    Student student;
    Faculty faculty;
    HOD hod;
    ConnectionHandler conHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        hideAll();
        b = (Button)findViewById(R.id.button);
        userType =(Spinner) findViewById(R.id.userType);
        result = findViewById(R.id.resultAlert);
        userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                result.setVisibility(INVISIBLE);
                if(position==0)
                    findViewById(R.id.button).setEnabled(false);
                else
                    findViewById(R.id.button).setEnabled(true);
                if(position!=0 && registration){

                    clearInput();
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



    public void toRegistration(View view){
        registration = true;
        hideAll();
        userType.setSelection(0);
        showRegistration();
    }

    public void toLogin(View view){
        registration = false;
        hideAll();
        userType.setSelection(0);
        createLoginForm();
    }

    protected void showRegistration() {
        findViewById(R.id.logo).setVisibility(VISIBLE);
        findViewById(R.id.spaceBetween).setVisibility(VISIBLE);
        findViewById(R.id.registrationTitle).setVisibility(VISIBLE);

        userType.setVisibility(VISIBLE);

        findViewById(R.id.loginLabel).setVisibility(VISIBLE);
        findViewById(R.id.loginLink).setVisibility(VISIBLE);
    }

    protected void createRegistrationForm(int position){
        EditText name = (EditText)findViewById(R.id.name);
        EditText id = (EditText)findViewById(R.id.facId);

        findViewById(R.id.logo).setVisibility(GONE);
        findViewById(R.id.name).setVisibility(VISIBLE);
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
            findViewById(R.id.branch).setVisibility(VISIBLE);
            findViewById(R.id.facId).setVisibility(GONE);
            userType.setSelection(1);
        }
        else if(position == 2){
            name.setHint("Faculty Name");
            id.setHint("Faculty ID");
            findViewById(R.id.facId).setVisibility(VISIBLE);
            userType.setSelection(2);
            findViewById(R.id.en).setVisibility(GONE);
            findViewById(R.id.sem).setVisibility(GONE);
            findViewById(R.id.div).setVisibility(GONE);
            findViewById(R.id.branch).setVisibility(VISIBLE);
        }else if(position == 3){
            name.setHint("Head of Department Name");
            id.setHint("Head of Department ID");
            findViewById(R.id.facId).setVisibility(VISIBLE);
            userType.setSelection(3);
            findViewById(R.id.en).setVisibility(GONE);
            findViewById(R.id.sem).setVisibility(GONE);
            findViewById(R.id.div).setVisibility(GONE);
            findViewById(R.id.branch).setVisibility(VISIBLE);
        }
    }

    private void createLoginForm(){
        clearInput();
        //LOGO visible
        findViewById(R.id.logo).setVisibility(VISIBLE);

        findViewById(R.id.loginTitle).setVisibility(VISIBLE);
        findViewById(R.id.spaceBetween).setVisibility(VISIBLE);

        //Spinner visible
        userType.setVisibility(VISIBLE);

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
        findViewById(R.id.sem).setVisibility(GONE);
        findViewById(R.id.div).setVisibility(GONE);
        findViewById(R.id.facId).setVisibility(GONE);
        findViewById(R.id.spaceBetween).setVisibility(GONE);
        findViewById(R.id.button).setVisibility(GONE);
        findViewById(R.id.en).setVisibility(GONE);
        findViewById(R.id.registrationTitle).setVisibility(GONE);
        findViewById(R.id.loginLabel).setVisibility(GONE);
        findViewById(R.id.branch).setVisibility(GONE);
        findViewById(R.id.resultAlert).setVisibility(INVISIBLE);
    }

    private void clearInput(){

        EditText password = (EditText)findViewById(R.id.password);
        password.setText("");

        EditText email = (EditText)findViewById(R.id.email);
        email.setText("");

        EditText name = (EditText)findViewById(R.id.name);
        name.setText("");

        Spinner sem = (Spinner)findViewById(R.id.sem);
        sem.setSelection(0);

        Spinner div = (Spinner)findViewById(R.id.div);
        div.setSelection(0);

        Spinner branch = (Spinner)findViewById(R.id.branch);
        branch.setSelection(0);

        EditText facId = (EditText)findViewById(R.id.facId);
        facId.setText("");

        EditText en = (EditText)findViewById(R.id.en);
        en.setText("");
    }


    public void submit(View view){
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        int userTypeInt = 0;
        if(userType.getSelectedItemPosition() == 1)
            userTypeInt = 1;
        else if(userType.getSelectedItemPosition() == 2)
            userTypeInt = 2;
        else if(userType.getSelectedItemPosition() == 3)
            userTypeInt = 3;
        if(registration){
            fname = (EditText)findViewById(R.id.name);
            branch = (Spinner)findViewById(R.id.branch);

            String branchString = returnBranch(branch.getSelectedItemPosition());

            if(userTypeInt == 1){
                en = (EditText)findViewById(R.id.en);
                sem = (Spinner)findViewById(R.id.sem);
                div = (Spinner)findViewById(R.id.div);
                String divString = returnDiv(div.getSelectedItemPosition());
                student = new Student(Long.parseLong(en.getText().toString()), fname.getText().toString(), branchString, sem.getSelectedItemPosition(), divString, email.getText().toString(), password.getText().toString());
                conHandle = new ConnectionHandler(student, registration, MainActivity.this);
            }
            else if(userTypeInt == 2) {
                facId = (EditText) findViewById(R.id.facId);
                faculty = new Faculty(facId.getText().toString(), fname.getText().toString(), branchString, email.getText().toString(), password.getText().toString());
                conHandle = new ConnectionHandler(faculty, registration, MainActivity.this);
            }
            else if(userTypeInt == 3){
                facId = (EditText)findViewById(R.id.facId);
                hod = new HOD(facId.getText().toString(), fname.getText().toString(), branchString, email.getText().toString(), password.getText().toString());
                conHandle = new ConnectionHandler(hod, registration, MainActivity.this);
            }

        }
        else{
            if(userTypeInt == 1){
                student = new Student(userTypeInt, email.getText().toString(), password.getText().toString());
                conHandle = new ConnectionHandler(student, registration, MainActivity.this);
            }
            else if(userTypeInt == 2){
                faculty = new Faculty(userTypeInt, email.getText().toString(), password.getText().toString());
                conHandle = new ConnectionHandler(faculty, registration, MainActivity.this);
            }
            else if(userTypeInt == 3){
                hod = new HOD(userTypeInt, email.getText().toString(), password.getText().toString());
                conHandle = new ConnectionHandler(hod, registration, MainActivity.this);
            }
        }
    }

    String returnBranch(int pos){
        if(pos == 1)
            return "mechanical";
        else if(pos == 2)
            return "computer";
        else if(pos == 3)
            return "automobile";
        else if(pos == 4)
            return "electrical";
        else if(pos == 5)
            return "ec";
        else if(pos == 6)
            return "civil";
        else if(pos == 6)
            return "IT";
        else
            return "Undefined";
    }

    String returnDiv(int pos){
        if(pos == 1)
            return "A";
        else if(pos == 2)
            return "B";
        else
            return "Undefined";

    }
}

