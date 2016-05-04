package com.example.dmk.quizzproject.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dmk.quizzproject.AuthentificationActivity;
import com.example.dmk.quizzproject.Fragment.Profil;
import com.example.dmk.quizzproject.Fragment.SelectComplexity;
import com.example.dmk.quizzproject.Fragment.SelectTheme;
import com.example.dmk.quizzproject.Fragment.SelectTypeGame;
import com.example.dmk.quizzproject.Fragment.Settings;
import com.example.dmk.quizzproject.Fragment.guillotine;

import com.example.dmk.quizzproject.PageFragment;
import com.example.dmk.quizzproject.ProfileFragment;

import com.example.dmk.quizzproject.QuestionActivity;
import com.example.dmk.quizzproject.R;
import com.example.dmk.quizzproject.core.Person;

import com.example.dmk.quizzproject.core.Question;




import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, guillotine.ClickButtonGuillotineLisner, SelectTheme.ClickButtonThemeLisner, Profil.ClickButtonLisner, Settings.ClickButtonLisner, SelectComplexity.ClickButtonComplexityLisner, SelectTypeGame.ClickButtonTypeGameLisner {
    private static final long RIPPLE_DURATION = 250;
    ProfileFragment fragment ;
    private ArrayList<Person> mPersonsList = new ArrayList<Person>();
    private static final String PERSONS_LIST_KEY = "persons_list_key";
    private Toolbar toolbar;

    public static ArrayList<Question> questionList = new ArrayList<>();

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        launchProfile();
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setOnClickListener(this);


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


        getSupportFragmentManager().beginTransaction()
               .replace(R.id.content, PageFragment.newInstance()).commit();


    }

    private void launchSettings() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).replace(R.id.content, Settings.newInstance(this)).commit();


    }

    private void launchProfile() {

       readJson();


        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).replace(R.id.content, Profil.newInstance(this,mPersonsList)).commit();


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
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PERSONS_LIST_KEY, mPersonsList);

    }
    private void launchAdd() {
        Log.v("launchAdd() --->"," Called");
        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, PageFragment.newInstance()).commit();
    }

    private void performPersonAdd(String name, int age) {

        Log.v("performPersonAdd() --->"," Called");
        Person person = new Person(name, age,0);
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

    public String loadJSONFromAsset() {
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
            for(int i =0;i<formList.size();i++){

                Log.v("Samir tarhouni ",formList.get(i).toString());
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
