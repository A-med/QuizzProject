package com.example.iit.quizzproject.activity;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.iit.quizzproject.core.QuestionInsert;
import com.example.iit.quizzproject.core.QuestionLang;
import com.example.iit.quizzproject.database.TestContentProvider;
import com.example.iit.quizzproject.database.tables.QuestionLangage;
import com.example.iit.quizzproject.database.tables.QuestionListSqlite;
import com.example.iit.quizzproject.fragment.Profil;
import com.example.iit.quizzproject.fragment.SelectComplexity;
import com.example.iit.quizzproject.fragment.SelectTheme;
import com.example.iit.quizzproject.fragment.SelectTypeGame;
import com.example.iit.quizzproject.fragment.Settings;
import com.example.iit.quizzproject.fragment.guillotine;

import com.example.iit.quizzproject.ProfileFragment;

import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.Person;

import com.example.iit.quizzproject.core.Question;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.ArrayList;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, guillotine.ClickButtonGuillotineLisner, SelectTheme.ClickButtonThemeLisner, Profil.ClickButtonLisner, Settings.ClickButtonLisner, SelectComplexity.ClickButtonComplexityLisner, SelectTypeGame.ClickButtonTypeGameLisner {
    private static final long RIPPLE_DURATION = 250;
    ProfileFragment fragment;
    private ArrayList<Person> mPersonsList = new ArrayList<Person>();
    private static final String PERSONS_LIST_KEY = "persons_list_key";
    public static final String EXTRA_MESSAGE = "";
    Fragment themeFragment;
    private Toolbar toolbar;
    String theme;
    int randQuestionId;
    public static ArrayList<Question> questionList = new ArrayList<>();

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
    public static  int connectWithFb;
    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Intent intent = getIntent();

        if(intent.getStringExtra("replay")!=null)
        {
            launchPlay();
        }
        else {


            launchProfile();
        }
        try {
            verifSqliteParseSize();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkDataBase();
        selectAllLangage();

        if(connectWithFb==1) {
            Bundle inBundle = getIntent().getExtras();
            String name = inBundle.get("name").toString();
            String surname = inBundle.get("surname").toString();
            String imageUrl = inBundle.get("imageUrl").toString();

            Log.v("name et lastname image", "" + name + " " + surname + " " + imageUrl);

            Profil.nameValue = name;
            Profil.lastNameValue = surname;
            Profil.imageValue = imageUrl;
        }
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setOnClickListener(this);

        //SQLiteOpenHelper sql = new SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version);

        // Uri uri=getContentResolver().insert(  cprovider.tableuri, contentValues);
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
    public void selectAllLangage(){
        Cursor mCursor = getContentResolver().query(TestContentProvider.RECORDS_CONTENT_URI_Question, QuestionListSqlite.PROJECTION_ALL, null, null, null);

        while(mCursor.moveToNext()){
            Log.v("select sql",mCursor.getString(1) );

        }
    }
    public void getAllParseQuestions()
    {

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("questions");
        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for(int i=0;i<objects.size();i++)
                {
                    QuestionInsert qi = new QuestionInsert();
                    qi.setId((Integer) objects.get(i).get("id_o"));
                    qi.setIdP1((Integer) objects.get(i).get("id_p1"));
                    qi.setIdP2((Integer) objects.get(i).get("id_p2"));
                    qi.setIdP3((Integer) objects.get(i).get("id_p3"));
                    qi.setIdQ((Integer) objects.get(i).get("id_q"));
                    qi.setIdRep((Integer) objects.get(i).get("id_rep"));
                    insertDBQuestion(qi);


                }
            }
        });


    }


    public void insertDBQuestion(com.example.iit.quizzproject.core.QuestionInsert q) {
        String line;
        ContentValues contentValues = new ContentValues();
        Uri uri;
        try {

            contentValues.put(QuestionListSqlite.id_QUESTION, q.getIdQ());

            contentValues.put(QuestionListSqlite.id_PROPOSITION1, q.getIdP1());

            contentValues.put(QuestionListSqlite.id_PROPOSITION2, q.getIdP2());

            contentValues.put(QuestionListSqlite.id_PROPOSITION3, q.getIdP3());


            contentValues.put(QuestionListSqlite.id_ANSWER, q.getIdRep());
            uri = getContentResolver().insert(
                    TestContentProvider.RECORDS_CONTENT_URI_Question,
                    contentValues);
            contentValues.clear();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAllParseLanguage()
    {

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("langage");
        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for(int i=0;i<objects.size();i++)
                {
                    QuestionLang ql = new QuestionLang();
                    ql.setEn((String)objects.get(i).get("en"));
                    ql.setFr((String)objects.get(i).get("fr"));
                    ql.setId((Integer) objects.get(i).get("id_o"));
                    insertDBLangage(ql);

                }
                getAllParseQuestions();
            }
        });


    }
    public void insertDBLangage(QuestionLang q ) {

        ContentValues contentValues = new ContentValues();
        Uri uri;
        try
        {
            contentValues.put(QuestionLangage.FRANCAIS, q.getFr());

            contentValues.put(QuestionLangage.ANGLAIS, q.getEn());
            uri = getContentResolver().insert(
                    TestContentProvider.RECORDS_CONTENT_URI_LANGAGE,
                    contentValues);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public int parseQuestionsSize() throws ParseException {
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("questions");
        List<ParseObject> pos = query1.find();
        return  pos.size();
    }

    public int selectSumQuestionSqlite()
    {
        int comp=0;
        Cursor mCursor = getContentResolver().query(TestContentProvider.RECORDS_CONTENT_URI_Question, QuestionListSqlite.PROJECTION_ALL, null, null, null);
        while (mCursor.moveToNext()) {
            comp++;
        }
        return comp;
    }
    public void deleteTableSqlLite()
    {
        int uriQuestion=  getContentResolver().delete(TestContentProvider.RECORDS_CONTENT_URI_Question,null,null);
        int uriLangage=  getContentResolver().delete(TestContentProvider.RECORDS_CONTENT_URI_LANGAGE,null,null);
    }
    public int verifSqliteParseSize() throws ParseException {
        if(parseQuestionsSize()>selectSumQuestionSqlite() )
        {

            deleteTableSqlLite();
            getAllParseLanguage();

            return 1;
        }
        return 0;
    }

    @Override
    public void onClick(View v) {


    }

    private void launchToolbar(String fragment) {

        Fragment fragmentguillotine;

        if (fragment.equals("profil")) {
            fragmentguillotine = guillotine.newInstance(this, 1);

        } else if (fragment.equals("play")) {
            fragmentguillotine = guillotine.newInstance(this, 2);

        } else {
            fragmentguillotine = guillotine.newInstance(this, 3);
        }

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out)
                .add(R.id.content, fragmentguillotine)
                .addToBackStack(null).commit();


    }

    private void launchPlay() {

        themeFragment = SelectTheme.newInstance(this);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).replace(R.id.content,themeFragment).commit();


    }

    private void launchSettings() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).replace(R.id.content, Settings.newInstance(this)).commit();


    }

    private void launchProfile() {

       // readJson();


        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).replace(R.id.content, Profil.newInstance(this, mPersonsList)).commit();


    }


    void exitGuillotine(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out)
                .remove(fragment)
                .addToBackStack(null).commit();

    }


    @Override
    public void onFinishClickTollbarPlay() {

        launchToolbar("play");
    }

    @Override
    public void onFinishChoiceTheme(String theme) {
        this.theme=theme;
        launchGameType();

    }

    void launchComplexity() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).add(R.id.content, SelectComplexity.newInstance(this)).commit();
    }

    void launchGameType() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).add(R.id.content, SelectTypeGame.newInstance(this)).commit();
    }

    @Override
    public void onFinishClickButtonProfile() {

        launchToolbar("profil");


    }

    @Override
    public void onFinishClickButtonSettings() {

        launchToolbar("settings");


    }


    @Override
    public void onFinishClickTollbarGuillotine(Fragment fragment) {
        exitGuillotine(fragment);

    }

    @Override
    public void onFinishClickPlayGuillotine(Fragment fragment) {
        exitGuillotine(fragment);
        launchPlay();

    }

    @Override
    public void onFinishClickSettingGuillotine(Fragment fragment) {
        exitGuillotine(fragment);
        launchSettings();

    }

    @Override
    public void onFinishClickProfilGuillotine(Fragment fragment) {
        exitGuillotine(fragment);
        launchProfile();

    }


    @Override
    public void onFinishSelectTypeGame(String type, Fragment f) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out)
                .remove(f).addToBackStack(null).commit();
        Log.v("iit", "complexity");
        if (type.equals("mono"))
            launchComplexity();

    }

    @Override
    public void onClickCloseTypeGame(Fragment f) {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out)
                .remove(f).addToBackStack(null).commit();
        ((SelectTheme)themeFragment).openListner(theme);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PERSONS_LIST_KEY, mPersonsList);

    }

    private void launchAdd() {
        Log.v("launchAdd() --->", " Called");
        getFragmentManager().beginTransaction().replace(R.id.content_fragment, ProfileFragment.newInstance(mPersonsList)).commit();
    }

    private void performPersonAdd(String name, int age) {

        Log.v("performPersonAdd() --->", " Called");
        Person person = new Person(name, age, 0);
        mPersonsList.add(person);


        getFragmentManager().beginTransaction().replace(R.id.content_fragment, ProfileFragment.newInstance(mPersonsList)).commit();

        Log.v("performPersonAdd() --->", " Called");

        mPersonsList.add(person);


        getFragmentManager().beginTransaction().replace(R.id.content_fragment, ProfileFragment.newInstance(mPersonsList)).commit();
    }


    private String importFromFileJson(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;
        } catch (IOException e) {
            Log.v("iit", e.getMessage());
            return null;
        }

    }



   /* public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("questionJson");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    void readJson() {

        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray m_jArry = obj.getJSONArray("question");

            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Question question = new Question();
                Log.d("Details-->", jo_inside.getString("proposition1"));
                String quest = jo_inside.getString("text_question");


                String prop_1 = jo_inside.getString("proposition1");
                String prop_2 = jo_inside.getString("proposition2");
                String prop_3 = jo_inside.getString("proposition3");
                String answer = jo_inside.getString("answer");

                question.setText_question(quest);
                question.setProposition1(prop_1);
                question.setProposition2(prop_2);
                question.setProposition3(prop_3);
                question.setAnswer(answer);

                questionList.add(question);

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("text_question", quest);
                m_li.put("proposition1", prop_1);
                m_li.put("proposition2", prop_2);
                m_li.put("proposition3", prop_3);
                m_li.put("answer", answer);

                formList.add(m_li);
            }
            for (int i = 0; i < formList.size(); i++) {

                Log.v("Json Question ", formList.get(i).toString());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }*/
    public void startQuestionActivity(String comp)
    {
        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
        intent.putExtra(EXTRA_MESSAGE, comp);
        startActivity(intent);
        finish();
    }
    @Override
    public void onFinishClickEasyComplexity(String comp, Fragment f) {
        startQuestionActivity(comp);
    }
    @Override
    public void onFinishClickMediumComplexity(String comp, Fragment f) {
        startQuestionActivity(comp);
    }
    @Override
    public void onFinishClickHardComplexity(String comp, Fragment f) {
        startQuestionActivity(comp);
    }

    @Override
    public void onFinishClickCloseComplexity(Fragment f) {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out)
                .remove(f).addToBackStack(null).commit();
        launchGameType();


    }
}