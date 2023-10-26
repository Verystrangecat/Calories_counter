package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_register extends AppCompatActivity {
    EditText name, password, checkpassword, email;
    Button signup;
    TextView link_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUi();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();

            }
        });


        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity_register.this, Login.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void setupUi() {
        name = findViewById(R.id.editTextname);
        password = findViewById(R.id.editText_signup_password);
        checkpassword = findViewById(R.id.editText_signup_passwordcheck);
        email = findViewById(R.id.editText_signup_email);
        signup = findViewById(R.id.btncreateaccount);
        link_login=findViewById(R.id.text_link_login);
    }

    private void checkDataEntered() {
        boolean isvalid = true;
        if (isEmpty(name)) {
            name.setError("You must enter your name to sign up");
            isvalid = false;
        }
        if (isEmpty(email)) {
            email.setError("You must enter your e-mail to sign up");
            isvalid = false;
        }
        if (isEmpty(password)) {
            password.setError("You must enter a password to sign up");
            isvalid = false;
        }
        if (isEmpty(checkpassword)) {
            checkpassword.setError("You must confirm the password to sign up");
            isvalid = false;}
        if(!isEmail(email)){
            email.setError("You must enter valid email to sign up");
            isvalid=false;
        }
        if(isvalid){
            String passtext=password.getText().toString();
            String checkpasstext=checkpassword.getText().toString();
            if(passtext.equals(checkpasstext)){
                Intent i=new Intent(Activity_register.this, Setup_account.class);
                startActivity(i);
                finish();

                //Todo:start new activity, getting information
            }
            else{
                checkpassword.setError("Is not the same as the password");

            }}


    }

    public boolean isEmpty (EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    public boolean isEmail(EditText text){
        CharSequence str=text.getText().toString();
        return !TextUtils.isEmpty(str)&& Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }
}
//Todo:take care of the password and login