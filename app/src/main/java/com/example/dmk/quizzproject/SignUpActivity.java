package com.example.dmk.quizzproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

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
