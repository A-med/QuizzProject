package com.example.iit.quizzproject.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.iit.quizzproject.AuthentificationActivity;
import com.parse.ParseUser;

public class RedirectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if(ParseUser.getCurrentUser()!= null)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
       }
        else
        {
            Intent intent = new Intent(getApplicationContext(), AuthentificationActivity.class);
            startActivity(intent);

        }
       finish();
    }
}
