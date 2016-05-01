package com.example.dmk.quizzproject.Activity;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.dmk.quizzproject.Fragment.Profil;
import com.example.dmk.quizzproject.Fragment.SelectTheme;
import com.example.dmk.quizzproject.Fragment.Settings;
import com.example.dmk.quizzproject.Fragment.guillotine;

import com.example.dmk.quizzproject.R;



public class MainActivity extends AppCompatActivity implements View.OnClickListener, guillotine.ClickButtonGuillotineLisner, SelectTheme.ClickButtonPlayLisner, Profil.ClickButtonLisner, Settings.ClickButtonLisner {
    private static final long RIPPLE_DURATION = 250;

    private Toolbar toolbar;

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

        Fragment fragmentguillotine ;

        if(fragment.equals("profil"))
        {
            fragmentguillotine = guillotine.newInstance(this,1);

        }
        else if(fragment.equals("play"))
        {
            fragmentguillotine = guillotine.newInstance(this,2);

        }
        else
        {
            fragmentguillotine = guillotine.newInstance(this,3);
        }

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .add(R.id.content,fragmentguillotine)
                .addToBackStack(null).commit();


    }

    private void launchPlay() {


        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out).replace(R.id.content, SelectTheme.newInstance(this)).commit();


    }

    private void launchSettings() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out).replace(R.id.content, Settings.newInstance(this)).commit();


    }

    private void launchProfile() {


        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out).replace(R.id.content, Profil.newInstance(this)).commit();


    }






    void exitGuillotine(Fragment fragment)
    {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .remove(fragment)
                .addToBackStack(null).commit();

    }





    @Override
    public void onFinishClickTollbarPlay() {

            launchToolbar("play");




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


}