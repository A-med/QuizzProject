package com.example.iit.quizzproject.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iit.quizzproject.InviteFriends;
import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.Person;
import com.example.iit.quizzproject.ui.PersonAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profil extends Fragment implements View.OnClickListener {


    private static ArrayList<Person> mPersonsList = new ArrayList<Person>();
    private static ClickButtonLisner mClickButtonLisner;
    ListView androidListView;
    ImageView profil_Toolbar;
    private ListView mListView;
    private PersonAdapter mPersonAdapter;
    private TextView textAnim;
    private AppCompatButton appCompatButtonfriend;

    public Profil() {
        // Required empty public constructor
    }

    public static Fragment newInstance(ClickButtonLisner listener, ArrayList<Person> personsList) {
        Profil fragment = new Profil();
        //mPersonsList = personsList;
        mClickButtonLisner = listener;


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profil, container, false);
        profil_Toolbar = (ImageView) view.findViewById(R.id.profil_Toolbar);
        profil_Toolbar.setOnClickListener(this);
        appCompatButtonfriend = (AppCompatButton) view.findViewById(R.id.freinds);
        appCompatButtonfriend.setOnClickListener(this);
        mListView = (ListView) view.findViewById(R.id.list_view);
        // textAnim = (TextView) view.findViewById(R.id.anim);
        //textAnim.startAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out));

        mPersonAdapter = new PersonAdapter(getActivity().getApplicationContext(), mPersonsList);
        fillAdapter();
        mListView.setAdapter(mPersonAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profil_Toolbar:
                Log.v("---->", "Toolbar pressed --");
                openToolbar();
                break;

            case R.id.freinds:
                Log.v("---->", " openInviteFriend--");

                openInviteFriend();
                break;


        }
    }

    void openToolbar() {

        mClickButtonLisner.onFinishClickButtonProfile();

    }

    private void fillAdapter() {
        mPersonsList.clear();
        for (int i = 0; i < 3; i++) {
            mPersonsList.add(new Person("Friend " + i, i, i));
        }

        mPersonAdapter.notifyDataSetChanged();
    }

    void openInviteFriend() {
        Intent intentInviteFriend = new Intent(getActivity().getApplicationContext(), InviteFriends.class);
        startActivity(intentInviteFriend);
    }

    public interface ClickButtonLisner {
        public void onFinishClickButtonProfile();

    }


}
