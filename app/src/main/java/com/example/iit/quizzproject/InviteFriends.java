package com.example.iit.quizzproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class InviteFriends extends FragmentActivity implements AdapterView.OnItemClickListener {


    private ListView listView;
    private AdapterUser adapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.iit.quizzproject.R.layout.invite_friend);
        listView = (ListView) findViewById(com.example.iit.quizzproject.R.id.list);
        adapterUser = new AdapterUser(getApplicationContext(), getNewEntries());
        listView.setAdapter(adapterUser);
        listView.setOnItemClickListener(this);
        final View content = findViewById(android.R.id.content);
        switch (content.getId()) {
            case R.id.cbSelected:
                if ( ((CheckBox) content).isChecked()) {


                    Snackbar.make(listView, "ddd", Snackbar.LENGTH_SHORT).show();
                }

                else {

                    Snackbar.make(listView, "ddd", Snackbar.LENGTH_LONG).show();
                }
                break;
        }


    }



    private ArrayList<FriendsListItemWarpper> getNewEntries() {
        boolean b = false;
        final ArrayList<FriendsListItemWarpper> entries = new ArrayList<FriendsListItemWarpper>();

        for (int i = 1; i < 50; i++) {
            entries.add(
                    new FriendsListItemWarpper(new User(
                            "Amin Ben Mahfoudh ",
                            "24 ans ",


                            i % 2 == 0 ? com.example.iit.quizzproject.R.drawable.lurecas : com.example.iit.quizzproject.R.drawable.elisa,
                            b
                    ), false)
            );
        }

        return entries;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("hamdi","clicked position = "+position);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cbSelected);
        checkBox.toggle();
    }
}
