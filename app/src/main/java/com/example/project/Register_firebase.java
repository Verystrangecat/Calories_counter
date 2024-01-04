package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

public class Register_firebase extends AppCompatActivity implements View.OnClickListener {
    EditText name, password, checkpassword, email;
    Button signup;
    TextView link_login;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firebase);
        setupUi();
    }

    private void setupUi() {
        name = findViewById(R.id.editTextnamef);
        password = findViewById(R.id.editText_signup_passwordf);
        checkpassword = findViewById(R.id.editText_signup_passwordcheckf);
        email = findViewById(R.id.editText_signup_emailf);
        signup = findViewById(R.id.btncreateaccountf);
        signup.setOnClickListener(this);
        link_login = findViewById(R.id.text_link_loginf);
        link_login.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        String emails, passwords;
        if (view == signup) {

            emails = email.getText().toString();
            passwords = password.getText().toString();
            if (TextUtils.isEmpty(emails)) {
                email.setError("Please enter your email");
                return;
            }
            if (TextUtils.isEmpty(passwords)) {
                password.setError("Please enter your password");
                return;
            }
            if(!checkDataEntered())
                return;

            mAuth.createUserWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register_firebase.this, "Account created.",
                                        Toast.LENGTH_SHORT).show();
                                Array_class arrayClass=new Array_class();
                                SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(arrayClass);
                                editor.putString("MyObject", json);

                                editor.commit();
                                startActivity(new Intent(Register_firebase.this, Setup_account.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Register_firebase.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }


                    });
        } else if (view == link_login) {
            startActivity(new Intent(Register_firebase.this, Login_firebase.class));

        }


    }

    private boolean checkDataEntered() {
        if (isEmpty(name)) {
            name.setError("You must enter your name to sign up");
         return false;
        }
        if (isEmpty(checkpassword)) {
            checkpassword.setError("You must confirm the password to sign up");
            return false;

        }
        if(!isEmail(email)) {
            email.setError("You must enter valid email to sign up");
        return false;}
        String passtext=password.getText().toString();
        String checkpasstext=checkpassword.getText().toString();
        if(passtext.length()<6){
            password.setError("Password should consist of minimum 6 characters");
            return false;
        }
        if(passtext.equals(checkpasstext))
            return true;
        checkpassword.setError("Is not the same as the password");
        return false;



        }


    public boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    public boolean isEmail(EditText text){
        CharSequence str=text.getText().toString();
        return !TextUtils.isEmpty(str)&& Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }
}




