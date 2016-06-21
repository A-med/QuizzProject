package com.example.iit.quizzproject;

import android.app.Application;
import android.graphics.Typeface;


import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseACL;

public class App extends Application {
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canardExtraBold;

    @Override
    public void onCreate() {
        super.onCreate();
        initTypeface();


        Parse.enableLocalDatastore(this.getBaseContext());


        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HSRrDlyK6Z2oki4bfNCfZiWxOfC1rL9HQMUpSZlL")
                .clientKey("rK89iYwjw6dp2mY2JNLPEi4Dd8E86XH3NHE4GzRD")
                .server("https://parseapi.back4app.com").enableLocalDataStore().build());


        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }

    private void initTypeface() {
        canardExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);

    }
}
