package com.example.iit.quizzproject;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.Person;

import java.util.ArrayList;


/**
 * Created by dmk on 29/04/16.
 */
public class TestActivity extends AppCompatActivity  {
    ProfileFragment fragment ;
    private ArrayList<Person> mPersonsList = new ArrayList<Person>();
    private static final String PERSONS_LIST_KEY = "persons_list_key";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getFragmentManager().beginTransaction().replace(R.id.content_fragment, ProfileFragment.newInstance(mPersonsList)).commit();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(PERSONS_LIST_KEY, mPersonsList);

    }
    private void launchAdd() {
        Log.v("launchAdd() --->"," Called");
        getFragmentManager().beginTransaction().replace(R.id.content_fragment, ProfileFragment.newInstance(mPersonsList)).commit();
    }

    private void performPersonAdd(String name, int age) {
        Log.v("performPersonAdd() --->"," Called");
        Person person = new Person(name, age,0);
        mPersonsList.add(person);


        getFragmentManager().beginTransaction().replace(R.id.content_fragment, ProfileFragment.newInstance(mPersonsList)).commit();

    }
}
