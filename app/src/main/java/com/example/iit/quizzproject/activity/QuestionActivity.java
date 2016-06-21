package com.example.iit.quizzproject.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.ContentValues;

import android.content.Context;

import android.content.DialogInterface;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.example.iit.quizzproject.fragment.QuestionFragment;
import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.QuestionSqlite;
import com.example.iit.quizzproject.database.TestContentProvider;
import com.example.iit.quizzproject.database.tables.QuestionLangage;
import com.example.iit.quizzproject.database.tables.QuestionListSqlite;
import com.example.iit.quizzproject.fragment.Score;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class QuestionActivity extends AppCompatActivity implements QuestionFragment.ClickButtonLisner, Score.ClickButtonLisner, View.OnClickListener {

    ArrayList<QuestionSqlite> arrayQuestion;
    ArrayList<AppCompatButton> arrayCompatButton = new ArrayList<AppCompatButton>();
    public static int indexQuestion = 0;
    Button buttonLangue;
    QuestionFragment mQuestionFragment;
    public static int idLangue = 0;
    String ComplexityMode;
    int point = 0;
    ObjectAnimator animation;
    public static final String EXTRA_MESSAGE = "";
    boolean verifLanchingScore = false;


    /*khedmet sdiri*/
    TapBarMenu tapBarMenu;
    int stateMusique=0;

    ;
    int state = 0;
    Button buttonPlay;
    Button buttonVibrator;
    MediaPlayer mp;
    MediaPlayer mpLost;
    MediaPlayer mpWin;
    Vibrator v;

    int etat_v=1;

    /*fin khedmet sdiri*/


    @Override
    public void onBackPressed() {


        Log.v("finish", "activity finished");
        if (verifLanchingScore == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure to exit game ?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    animation.cancel();
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();


        } else {
            gameClosed();
        }


    }


    @Override
    protected void onPause() {
        super.onPause();

        if(stateMusique==1) {
            mp.stop();
        }

    }


    public void settingVibrator() {
        if (etat_v == 0) {


            etat_v=1;

            buttonVibrator.setBackground(getResources().getDrawable(R.drawable.ic_vibration_white_24dp));
            //Log.i("off","off"+state);
        } else if (etat_v == 1) {

            etat_v=0;
            buttonVibrator.setBackground(getResources().getDrawable(R.drawable.ic_smartphone_white_24dp));

        }
    }

    public void playsetVibrator(long time) {
        android.os.Vibrator vibrator = (android.os.Vibrator) this.getApplication().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);                 //   v.vibrate(500);Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(time);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_activity);
        indexQuestion = 0;
        buttonLangue = (Button) findViewById(R.id.langue);
        buttonLangue.setOnClickListener(this);
        buttonLangue.setText("ENG");
        checkDataBase();
        insertDBLangage();
        insertDBQuestion();
        selectQuestionRandom();

        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question1));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question2));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question3));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question4));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question5));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question6));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question7));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question8));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question9));
        arrayCompatButton.add((AppCompatButton) findViewById(R.id.question10));


        ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        animation = ObjectAnimator.ofInt(mProgress, "progress", 100);
        animation.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                buttonLangue.setVisibility(View.INVISIBLE);
                launchScoreFragment(false);
                verifLanchingScore = true;
                if(stateMusique==1) {
                    mp.stop();
                }



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
        if (ComplexityMode.equals("Easy")) {
            animation.setDuration(180000);
        }
        if (ComplexityMode.equals("Medium")) {
            animation.setDuration(120000);
        }
        if (ComplexityMode.equals("Hard")) {
            animation.setDuration(60000);
        }

        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        /*khdemdet Sdiri*/

        tapBarMenu = (TapBarMenu) findViewById(R.id.tapBarMenu);
        tapBarMenu.setOnClickListener(this);
        buttonPlay = (Button) findViewById(R.id.buttonplay);
        buttonPlay.setOnClickListener(this);
        buttonVibrator = (Button) findViewById(R.id.buttonvibrator);
        buttonVibrator.setOnClickListener(this);
        Intent intent = getIntent();
        Log.v("intent",intent.getIntExtra("vibration",20)+" ");
        Log.v("intent",intent.getIntExtra("musique",20)+" ");
        if(intent.getIntExtra("vibration",20)==1)
        {
            etat_v=1;


        }

        if(intent.getIntExtra("musique",20)==1)
        {
            stateMusique=0;
            editMusic();
        }



        lanchQuestionFragment();


    }

    public void lanchQuestionFragment() {
        mQuestionFragment = QuestionFragment.newInstance(this, arrayQuestion.get(indexQuestion));
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
        for (int i = 0; i < arrayQuestion.size(); i++) {
            Log.v("arrayQuestion", arrayQuestion.get(i).getTextQuestionAn());
            Log.v("arrayQuestion", arrayQuestion.get(i).getTextQuestionFr());
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

    public void lostSong()
    {
        mpLost = MediaPlayer.create(this, R.raw.wrong);
        mpLost.start();
        mpLost.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mpLost) {
                mpLost.release();


            }
        });
    }
    public void winSong()
    {
        mpWin = MediaPlayer.create(this, R.raw.win);
        mpWin.start();
        mpWin.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mpWin) {
                mpWin.release();


            }
        });
    }

    @Override
    public void onClickButtonChoiceRepance(Boolean reponce) {
        if (reponce == true) {

            if(stateMusique ==1)
            {
                winSong();
            }
            addScore();
            arrayCompatButton.get(indexQuestion).setBackgroundDrawable(getResources().getDrawable(R.drawable.cercle_question_green));

        } else {
            if(stateMusique ==1)
            {
                lostSong();
            }
            arrayCompatButton.get(indexQuestion).setBackgroundDrawable(getResources().getDrawable(R.drawable.cercle_question_red));
            if (etat_v==1) {


                playsetVibrator(500);
            }
        }
    }

    private void addScore() {
        if (ComplexityMode.equals("Easy")) {
            point += 10;
        }
        if (ComplexityMode.equals("Medium")) {
            point += 15;
        }
        if (ComplexityMode.equals("Hard")) {
            point += 20;
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
        buttonLangue.setVisibility(View.INVISIBLE);
        launchScoreFragment(true);
        verifLanchingScore = true;


    }


    public void launchScoreFragment(boolean b) {
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.animation_flot_y_in,
                R.animator.animation_flot_y_out,
                R.animator.animation_flot_y_in,
                R.animator.animation_flot_y_out)
                .replace(R.id.activity_partie, Score.newInstance(this, point, b)).commit();


    }

    public void changeLangue() {
        if (idLangue == 0) {
            idLangue = 1;
            mQuestionFragment.onChangeLangue(idLangue);
            buttonLangue.setText("ENG");
        } else {
            idLangue = 0;
            mQuestionFragment.onChangeLangue(idLangue);
            buttonLangue.setText("FR");
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.langue):
                changeLangue();

                break;
            case R.id.buttonplay:
                editMusic();
                break;
            case R.id.buttonvibrator:
                Log.i("TAG", "button vibrator selected");
                settingVibrator();
                break;
            case R.id.tapBarMenu:
                Log.i("tap", "Tap bar menu selected");
                tapBarMenu.toggle();
                break;

        }
    }

    public void editMusic() {

        if (stateMusique == 1) {

            stateMusique=0;
            buttonPlay.setBackground(getResources().getDrawable(R.drawable.volume_up));

            mp.stop();

        } else {
            stateMusique=1;
            Log.i("TAG", "volume_off");
            buttonPlay.setBackground(getResources().getDrawable(R.drawable.volume_off));

            mp = MediaPlayer.create(getApplicationContext(), R.raw.soundtrack);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();


                }
            });
        }
    }

    public void gameClosed() {
        finish();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("musique",stateMusique);
        intent.putExtra("vibration",etat_v);
        startActivity(intent);
    }

    @Override
    public void closeGame() {
        MainActivity.connectWithFb=0;
        gameClosed();


    }

    @Override
    public void replayGame() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("replay", "replay");
        MainActivity.connectWithFb=0;
        startActivity(intent);

    }
}

