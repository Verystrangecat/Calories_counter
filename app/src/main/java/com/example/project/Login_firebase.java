package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_firebase extends AppCompatActivity implements View.OnClickListener{
    EditText login, password;
    Button bntlog, short_cut;
    TextView link;
    Animation anim_button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);
        setupUi();
    }

    private void setupUi() {
        login = findViewById(R.id.edittext_login_emailf);
        password = findViewById(R.id.edittext_login_passwordf);
        bntlog = findViewById(R.id.btnLoginf);
        link = findViewById(R.id.text_link_signupf);
        short_cut = findViewById(R.id.btn_shortcutf);
        anim_button = AnimationUtils.loadAnimation(this, R.anim.login_button);
        mAuth=FirebaseAuth.getInstance();
        bntlog.setOnClickListener(this);
        link.setOnClickListener(this);
        short_cut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String emails, passwords;
        if(view==bntlog){
            emails=login.getText().toString();
            passwords=password.getText().toString();
            if(TextUtils.isEmpty(emails)){
                login.setError("Please enter your email");
                return;}
            if(TextUtils.isEmpty(passwords)){
                password.setError("Please enter your password");
                return;}
            bntlog.startAnimation(anim_button);

            mAuth.signInWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Login_firebase.this, "Login successful.",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login_firebase.this, Main_screen.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Login_firebase.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

        } else if (view==link) {
            startActivity(new Intent(Login_firebase.this,Register_firebase.class));

        }
        else if (view==short_cut){
            startActivity(new Intent(Login_firebase.this, Main_screen.class));
            finish();
        }
    }

    //Todo add the forgot password thing
}