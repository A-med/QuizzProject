package com.example.iit.quizzproject;

import android.app.Application;
import android.graphics.Typeface;

<<<<<<< HEAD:app/src/main/java/com/example/iit/quizzproject/App.java
=======

>>>>>>> dev_test:app/src/main/java/com/example/dmk/quizzproject/App.java
public class App extends Application {
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroExtraBold;

    @Override
    public void onCreate() {
        super.onCreate();
        initTypeface();
    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);

    }
}
