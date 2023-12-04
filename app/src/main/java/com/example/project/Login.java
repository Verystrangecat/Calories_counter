package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText login, password;
    Button bntlog, short_cut;
    TextView link;
    Animation anim_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUi();
        setupListeners();
    }
    private void setupUi(){
        login=findViewById(R.id.edittext_login_email);
        password=findViewById(R.id.edittext_login_password);
        bntlog=findViewById(R.id.btnLogin);
        link=findViewById(R.id.text_link_signup);
        short_cut=findViewById(R.id.btn_shortcut);
        anim_button= AnimationUtils.loadAnimation(this,R.anim.login_button);

    }

    private void setupListeners(){
        bntlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkusername();
            }


        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, Activity_register.class);
                startActivity(intent);

            }
        });
        short_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, Main_screen.class);
                startActivity(intent);

            }
        });



    }

    private void checkusername(){
        boolean isvalid=true;
        if(isEmpty(login)){
            login.setError("You must enter username to login");
            isvalid=false;
        }
        else if(!isEmail(login)){
            login.setError("You must enter valid email");
            isvalid=false;
        }
        if(isvalid){
            String logtext=login.getText().toString();
            String pastext=password.getText().toString();
            if (logtext.equals("example@gmail.com") && pastext.equals("12345")){
                bntlog.startAnimation(anim_button);
                //TODO:add activity and check that the login and password changed
                // forgot password

            }
            else {
                Toast t= Toast.makeText(this, "Wrong email or password",Toast.LENGTH_SHORT);
                t.show();
            }

        }

    }

    public boolean isEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);

    }
    public boolean isEmail(EditText text){
        CharSequence str=text.getText().toString();
        return !TextUtils.isEmpty(str)&& Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }
}