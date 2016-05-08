package com.example.iit.quizzproject.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.iit.quizzproject.fragment.Profil;
import com.example.iit.quizzproject.fragment.SelectComplexity;
import com.example.iit.quizzproject.fragment.SelectTheme;
import com.example.iit.quizzproject.fragment.SelectTypeGame;
import com.example.iit.quizzproject.fragment.Settings;
import com.example.iit.quizzproject.fragment.guillotine;
import com.example.iit.quizzproject.PageFragment;
import com.example.iit.quizzproject.ProfileFragment;
import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.Person;
import com.example.iit.quizzproject.core.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, guillotine.ClickButtonGuillotineLisner, SelectTheme.ClickButtonThemeLisner, Profil.ClickButtonLisner, Settings.ClickButtonLisner, SelectComplexity.ClickButtonComplexityLisner, SelectTypeGame.ClickButtonTypeGameLisner {
    private static final long RIPPLE_DURATION = 250;
    private static final String PERSONS_LIST_KEY = "persons_list_key";
    public static ArrayList<Question> questionList = new ArrayList<>();
    ProfileFragment fragment;
    private ArrayList<Person> mPersonsList = new ArrayList<Person>();
    private Toolbar toolbar;

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


//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content, PageFragment.newInstance()).commit();


    }

    private void launchSettings() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_float_left_in,
                        R.animator.card_float_left_out,
                        R.animator.card_float_left_in,
                        R.animator.card_float_left_out).replace(R.id.content, Settings.newInstance(this)).commit();


    }

    private void launchProfile() {

      //  readJson();


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
        Log.v("launchAdd() --->", " Called");
     //   getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, PageFragment.newInstance()).commit();
    }

    private void performPersonAdd(String name, int age) {

        Log.v("performPersonAdd() --->", " Called");
        Person person = new Person(name, age, 0);
        mPersonsList.add(person);
        getFragmentManager().beginTransaction().replace(R.id.content_fragment, ProfileFragment.newInstance(mPersonsList)).commit();
    }






}
