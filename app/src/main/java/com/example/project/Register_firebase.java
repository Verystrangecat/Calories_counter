package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        String emails, passwords;
        if (view == signup) {
            emails=email.getText().toString();
            passwords=password.getText().toString();
            if(TextUtils.isEmpty(emails)){
                email.setError("Please enter your email");
                return;}
            if(TextUtils.isEmpty(passwords)){
                password.setError("Please enter your password");
                return;}

            mAuth.createUserWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register_firebase.this, "Account created.",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register_firebase.this, Setup_account.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Register_firebase.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }


                    });
            }


        }
    }



