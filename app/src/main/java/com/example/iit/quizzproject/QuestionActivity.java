package com.example.iit.quizzproject;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iit.quizzproject.core.Question;
import com.example.iit.quizzproject.database.TestContentProvider;
import com.example.iit.quizzproject.database.tables.QuestionList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class QuestionActivity extends AppCompatActivity {



    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.example.iit.quizzproject.R.layout.activity_question);
        Resources res = getResources();

        checkDataBase();
        //  queryContentProviderWithResolver();


        insertDB();



        ProgressBar mProgress = (ProgressBar) findViewById(com.example.iit.quizzproject.R.id.circularProgressbar);
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        // Locate the viewpager in activity_main.xml
        ViewPager viewPager = (ViewPager) findViewById(com.example.iit.quizzproject.R.id.pager);
        // Set the ViewPagerAdapter into ViewPager
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),select()));

    }

    public  Vector select(){
        Cursor mCursor=getContentResolver().query(TestContentProvider.RECORDS_CONTENT_URI, QuestionList.PROJECTION_ALL,null,null,null );
        Vector<Question> questionList = new Vector<>();
        while (mCursor.moveToNext()) {
            Question question =new Question(mCursor.getString(1),mCursor.getString(2),mCursor.getString(3),mCursor.getString(4),mCursor.getString(5));
            questionList.add(question);

        }
        return questionList;

    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {

            checkDB = SQLiteDatabase.openDatabase("/data/data/com.example.iit.quizzproject/databases/quizz.db", null, SQLiteDatabase.OPEN_READWRITE);
            // Log.v("Base de donnee  ","TAble name"+SQLiteDatabase.findEditTable("questions"));
            Log.v("Base de donnee","Base created Path="+checkDB.getPath()+"   Version = "+checkDB.getVersion());


            checkDB.close();
        } catch (SQLiteException e) {
            // base de données n'existe pas.
            Toast.makeText(QuestionActivity.this, "Base not created", Toast.LENGTH_SHORT).show();
            //  Log.v("Record URI",TestContentProvider.RECORDS_CONTENT_URI.toString());
        }
        return checkDB != null ? true : false;
    }
    public void insertDB() {
        String line;
        ContentValues contentValues = new ContentValues();
        Uri uri;
        try
        {
            InputStream is = getAssets().open("questionJson");
            BufferedReader r = new BufferedReader(new InputStreamReader(is));

            while((line=r.readLine())!=null)
            {
                Log.v("line",line);
                contentValues.put(QuestionList.QUESTION, line);
                r.readLine();
                contentValues.put(QuestionList.PROPOSITION1, line);
                r.readLine();
                contentValues.put(QuestionList.PROPOSITION2, line);
                r.readLine();
                contentValues.put(QuestionList.PROPOSITION3, line);
                r.readLine();
                contentValues.put(QuestionList.ANSWER, line);
                uri = getContentResolver().insert(
                        TestContentProvider.RECORDS_CONTENT_URI,
                        contentValues);
                contentValues.clear();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }


        //  Log.v("URI PATH",uri.getPath().toString());
    }




}

