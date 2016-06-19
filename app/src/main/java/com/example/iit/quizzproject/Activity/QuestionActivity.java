package com.example.iit.quizzproject.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.example.iit.quizzproject.fragment.QuestionFragment;
import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.QuestionSqlite;
import com.example.iit.quizzproject.database.TestContentProvider;
import com.example.iit.quizzproject.database.tables.QuestionLangage;
import com.example.iit.quizzproject.database.tables.QuestionListSqlite;
import com.example.iit.quizzproject.fragment.Score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class QuestionActivity extends AppCompatActivity implements  QuestionFragment.ClickButtonLisner, Score.ClickButtonLisner ,View.OnClickListener {

    ArrayList<QuestionSqlite> arrayQuestion;
    ArrayList<AppCompatButton> arrayCompatButton=new ArrayList<AppCompatButton>();
    public static int indexQuestion=0;
    Switch toggleButton;
    QuestionFragment mQuestionFragment;
    public static int idLangue=0;
    String ComplexityMode;
    int point=0;
    ObjectAnimator animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_activity);
        indexQuestion=0;
        toggleButton = (Switch) findViewById(R.id.langue);
        toggleButton.setOnClickListener(this);

        checkDataBase();
        insertDBLangage();
        insertDBQuestion();
        selectQuestionRandom();

        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question1));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question2));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question3));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question4));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question5));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question6));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question7));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question8));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question9));
        arrayCompatButton.add((AppCompatButton)findViewById(R.id.question10));




        ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        animation = ObjectAnimator.ofInt(mProgress, "progress", 100);
        animation.addListener(new Animator.AnimatorListener() {

                                  @Override
                                  public void onAnimationStart(Animator animation) {

                                  }

                                  @Override
                                  public void onAnimationEnd(Animator animation) {

                                      launchScoreFragment(false);

                                  }

                                  @Override
                                  public void onAnimationCancel(Animator animation) {

                                  }

                                  @Override
                                  public void onAnimationRepeat(Animator animation) {

                                  }

                              });
        ComplexityMode = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.v("ComplexityMode : ", ComplexityMode);
        if(ComplexityMode.equals("Easy")) {
            animation.setDuration(20000);
        }
        if(ComplexityMode.equals("Medium")){
            animation.setDuration(120000);
        }
        if(ComplexityMode.equals("Hard")){
            animation.setDuration(60000);
        }

        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();



        lanchQuestionFragment();


    }

public void lanchQuestionFragment()
{
    mQuestionFragment = QuestionFragment.newInstance(this,arrayQuestion.get(indexQuestion));
    getFragmentManager().beginTransaction()
            .setCustomAnimations(R.animator.card_float_right_in,
                    R.animator.card_float_left_out,
                    R.animator.card_float_right_in,
                    R.animator.card_float_left_out).replace(R.id.content, mQuestionFragment).commit();
}



    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {

            checkDB = SQLiteDatabase.openDatabase("/data/data/com.example.iit.quizzproject/databases/quizz.db", null, SQLiteDatabase.OPEN_READWRITE);
            // Log.v("Base de donnee  ","TAble name"+SQLiteDatabase.findEditTable("questions"));
            Log.v("Base de donnee", "Base created Path=" + checkDB.getPath() + "   Version = " + checkDB.getVersion());


            checkDB.close();
        } catch (SQLiteException e) {
            // base de données n'existe pas.
           ;
            //  Log.v("Record URI",TestContentProvider.RECORDS_CONTENT_URI.toString());
        }
        return checkDB != null ? true : false;
    }

    public void insertDBLangage() {
        String line;
        ContentValues contentValues = new ContentValues();
        Uri uri;
        try {
            InputStream is = getAssets().open("QuestionLange");
            BufferedReader r = new BufferedReader(new InputStreamReader(is));

            while ((line = r.readLine()) != null) {

                contentValues.put(QuestionLangage.FRANCAIS, line);
                line = r.readLine();
                contentValues.put(QuestionLangage.ANGLAIS, line);
                uri = getContentResolver().insert(
                        TestContentProvider.RECORDS_CONTENT_URI_LANGAGE,
                        contentValues);
                contentValues.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void insertDBQuestion() {
        String line;
        ContentValues contentValues = new ContentValues();
        Uri uri;
        try {
            InputStream is = getAssets().open("QuestionId");
            BufferedReader r = new BufferedReader(new InputStreamReader(is));

            while ((line = r.readLine()) != null) {
                Log.v("line", line);
                contentValues.put(QuestionListSqlite.id_QUESTION, line);
                line = r.readLine();
                contentValues.put(QuestionListSqlite.id_PROPOSITION1, line);
                line = r.readLine();
                contentValues.put(QuestionListSqlite.id_PROPOSITION2, line);
                line = r.readLine();
                contentValues.put(QuestionListSqlite.id_PROPOSITION3, line);
                line = r.readLine();
                contentValues.put(QuestionListSqlite.id_ANSWER, line);
                uri = getContentResolver().insert(
                        TestContentProvider.RECORDS_CONTENT_URI_Question,
                        contentValues);
                contentValues.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //  Log.v("URI PATH",uri.getPath().toString());
    }

    public void selectQuestionRandom() {
        int randQuestionId;
        arrayQuestion = new ArrayList<QuestionSqlite>();
        int[] idQuestion;
        String[] questionLangage;
        QuestionSqlite objQuestion;
        for (int i = 0; i < 10; i++) {
            randQuestionId = Math.round((float) Math.random() * 9) + 1;
            idQuestion = selectDBQuestion(randQuestionId);
            objQuestion = new QuestionSqlite();

            questionLangage = selectDBLangage(idQuestion[0]);
            objQuestion.setTextQuestionFr(questionLangage[0]);
            objQuestion.setTextQuestionAn(questionLangage[1]);

            questionLangage = selectDBLangage(idQuestion[1]);
            objQuestion.setProposition1Fr(questionLangage[0]);
            objQuestion.setProposition1An(questionLangage[1]);

            questionLangage = selectDBLangage(idQuestion[2]);
            objQuestion.setProposition2Fr(questionLangage[0]);
            objQuestion.setProposition2An(questionLangage[1]);

            questionLangage = selectDBLangage(idQuestion[3]);
            objQuestion.setProposition3Fr(questionLangage[0]);
            objQuestion.setProposition3An(questionLangage[1]);

            questionLangage = selectDBLangage(idQuestion[4]);
            objQuestion.setAnswerFr(questionLangage[0]);
            objQuestion.setAnswerAn(questionLangage[1]);

            arrayQuestion.add(objQuestion);
        }
        for(int i=0; i<arrayQuestion.size();i++)
        {
            Log.v("arrayQuestion",arrayQuestion.get(i).getTextQuestionAn());
            Log.v("arrayQuestion",arrayQuestion.get(i).getTextQuestionFr());
        }




    }


    public String[] selectDBLangage(int idLangage) {
        Cursor mCursor = getContentResolver().query(TestContentProvider.RECORDS_CONTENT_URI_LANGAGE, QuestionLangage.PROJECTION_ALL, QuestionLangage._ID + "=" + idLangage, null, null);
        String[] langageQuestion = new String[2];
        while (mCursor.moveToNext()) {
           /* Question question =new Question(mCursor.getString(1),mCursor.getString(2),mCursor.getString(3),mCursor.getString(4),mCursor.getString(5));
            questionList.add(question);*/

            langageQuestion[0] = mCursor.getString(1);
            langageQuestion[1] = mCursor.getString(2);


        }
        //return questionList;
        return langageQuestion;

    }


    public int[] selectDBQuestion(int idQuestion) {
        Cursor mCursor = getContentResolver().query(TestContentProvider.RECORDS_CONTENT_URI_Question, QuestionListSqlite.PROJECTION_ALL, QuestionListSqlite._ID + "=" + idQuestion, null, null);
        int[] tabQuestion = new int[5];

        while (mCursor.moveToNext()) {
           /* Question question =new Question(mCursor.getString(1),mCursor.getString(2),mCursor.getString(3),mCursor.getString(4),mCursor.getString(5));
            questionList.add(question);*/

            tabQuestion[0] = mCursor.getInt(1);
            tabQuestion[1] = mCursor.getInt(2);
            tabQuestion[2] = mCursor.getInt(3);
            tabQuestion[3] = mCursor.getInt(4);
            tabQuestion[4] = mCursor.getInt(5);

           // Log.v("id_Question",mCursor.getString(1)+" "+mCursor.getInt(2)+" "+mCursor.getString(3)+" "+mCursor.getInt(4)+" "+mCursor.getInt(5));


        }
        return tabQuestion;

    }

    @Override
    public void onClickButtonChoiceRepance(Boolean reponce) {
        if(reponce==true)
        {
            addScore();
            arrayCompatButton.get(indexQuestion).setBackgroundDrawable(getResources().getDrawable(R.drawable.cercle_question_green));

        }else{
            arrayCompatButton.get(indexQuestion).setBackgroundDrawable(getResources().getDrawable(R.drawable.cercle_question_red));
        }
    }

    private void addScore() {
        if(ComplexityMode.equals("Easy")) {
            point+=10;
        }
        if(ComplexityMode.equals("Medium")){
            point+=15;
        }
        if(ComplexityMode.equals("Hard")){
            point+=20;
        }
    }

    @Override
    public void onClickNextQuestion() {
        indexQuestion++;
        lanchQuestionFragment();

    }

    @Override
    public void endQuestion() {

        animation.end();
        launchScoreFragment(true);



    }

    public void launchScoreFragment(boolean b){
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_partie, Score.newInstance(this,point,b)).commit();

    }

    public void changeLangue()
    {
        if(idLangue==0) {
            idLangue=1;
            mQuestionFragment.onChangeLangue(idLangue);

        }
        else
        {
            idLangue=0;
            mQuestionFragment.onChangeLangue(idLangue);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.langue):
                changeLangue();

                break;
        }
    }


    @Override
    public void closeGame() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void replayGame() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);

    }
}

