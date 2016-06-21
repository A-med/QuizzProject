package com.example.iit.quizzproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.Arrays;

public class SignUpActivity extends Activity implements View.OnClickListener {

    private Button mBack;
    private Button mRegister;
    private EditText txtName;
    private EditText txtLastName;
    private EditText email;
    private EditText login;
    private EditText pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        txtName =(EditText)findViewById(R.id.input_first_name);
        txtLastName =(EditText)findViewById(R.id.input_last_name);
        email =(EditText)findViewById(R.id.input_email);
        login =(EditText)findViewById(R.id.input_login);
        pwd =(EditText)findViewById(R.id.input_password);
        mBack=(Button) findViewById(R.id.btn_back);
        mBack.setOnClickListener(this);
        mRegister=(Button) findViewById(R.id.btn_login);
        mRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                backToSignIn();
        break;
            case R.id.btn_login:
                registerParse(txtName.getText().toString(),txtLastName.getText().toString(),pwd.getText().toString(),login.getText().toString(),email.getText().toString());
                break;
        }


    }


    private void registerParse(String fname,String lname,String pwd,String login,String email)
    {
        Log.v("fnameee+++++",fname);
        Log.v("fnameee+++++",lname);
        Log.v("fnameee+++++",pwd);
        Log.v("fnameee+++++",login);
        Log.v("fnameee+++++",email);

        ParseUser pu = new ParseUser();
        pu.put("fname", fname);
        pu.put("lname",lname);
        pu.put("password", pwd);
        pu.put("username", login);
        pu.put("email", email);

        pu.saveInBackground();

    }

    private void backToSignIn(){
        Intent i= new Intent(getApplicationContext(),AuthentificationActivity.class);
        startActivity(i);
        finish();
    }
}
