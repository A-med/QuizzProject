package com.example.iit.quizzproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;

import java.util.Arrays;

public class SignUpActivity extends Activity implements View.OnClickListener {

    private Button mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        mBack =(Button)findViewById(R.id.btn_back);
        mBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                backToSignIn();
        break;
        }


    }


    private void backToSignIn(){
        Intent i= new Intent(getApplicationContext(),AuthentificationActivity.class);
        startActivity(i);
        finish();
    }
}
