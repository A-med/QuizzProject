package com.example.dmk.quizzproject.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dmk.quizzproject.R;
import com.example.dmk.quizzproject.core.Person;
import com.example.dmk.quizzproject.ui.PersonAdapter;
import com.example.dmk.quizzproject.widget.CanaroTextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profil extends Fragment implements View.OnClickListener {


    private static ArrayList<Person> mPersonsList = new ArrayList<Person>();
    ListView androidListView;
    ImageView profil_Toolbar;

    private ListView mListView;
    private PersonAdapter mPersonAdapter;
    private TextView textAnim ;


    private static ClickButtonLisner mClickButtonLisner;
    public Profil() {
        // Required empty public constructor
    }
    public static Fragment newInstance(ClickButtonLisner listener,ArrayList<Person> personsList){
        Profil fragment =  new Profil();
        //mPersonsList = personsList;
        mClickButtonLisner =listener;


        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profil, container, false);
        profil_Toolbar = (ImageView) view.findViewById(R.id.profil_Toolbar);
        profil_Toolbar.setOnClickListener(this);

        mListView = (ListView) view.findViewById(R.id.list_view);
        // textAnim = (TextView) view.findViewById(R.id.anim);
        //textAnim.startAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out));
        mPersonAdapter = new PersonAdapter(getActivity().getApplicationContext(), mPersonsList);
        fillAdapter();
        mListView.setAdapter(mPersonAdapter);

        return view ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profil_Toolbar:
                Log.v("---->","Toolbar pressed --");
                openToolbar();
                break;

        }
    }

    void openToolbar(){

        mClickButtonLisner.onFinishClickButtonProfile();

    }

    public interface ClickButtonLisner {
        public void onFinishClickButtonProfile();

    }







    private void fillAdapter() {
            mPersonsList.clear();
        for (int i = 0; i < 3; i++) {
            mPersonsList.add(new Person("Friend " +i, i, i));
        }

        mPersonAdapter.notifyDataSetChanged();
    }


}
