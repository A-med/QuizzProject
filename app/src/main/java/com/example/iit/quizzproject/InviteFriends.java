package com.example.iit.quizzproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class InviteFriends extends FragmentActivity {


    ListView listView;
    AdapterUser adapterUser;
    CheckBox cbSelected;
    ArrayList<String> selections = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.iit.quizzproject.R.layout.invite_friend);
        cbSelected = (CheckBox) findViewById(com.example.iit.quizzproject.R.id.cbSelected);
        listView = (ListView) findViewById(com.example.iit.quizzproject.R.id.list);
        adapterUser = new AdapterUser(this, com.example.iit.quizzproject.R.layout.list_item);

        listView.setAdapter(adapterUser);

        for (User entry : getNewsEntries()) {
            adapterUser.add(entry);
        }
    }


    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        int pos = listView.getPositionForView(view);
        User user = adapterUser.getItem(pos);
        switch (view.getId()) {
            case com.example.iit.quizzproject.R.id.cbSelected:
                if (checked) {
                    selections.add("user");
                    Snackbar.make(listView, user.getName(), Snackbar.LENGTH_SHORT).show();
                } else {
                    selections.remove("user");
                    Snackbar.make(listView, user.getName(), Snackbar.LENGTH_LONG).show();
                }
                break;
        }

        /*
        for (int i = 0 ;i <selections.size();i++) {
            Log.v("ll", selections.get(i));
        }*/
    }


    private List<User> getNewsEntries() {
        boolean b = true;
        final List<User> entries = new ArrayList<User>();

        for (int i = 1; i < 50; i++) {
            entries.add(
                    new User(
                            "Amin Ben Mahfoudh ",
                            "24 ans ",


                            i % 2 == 0 ? com.example.iit.quizzproject.R.drawable.lurecas : com.example.iit.quizzproject.R.drawable.elisa,
                            b
                    )
            );
        }
        return entries;
    }

}
