package com.example.dmk.quizzproject.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dmk.quizzproject.R;
import com.example.dmk.quizzproject.widget.CanaroTextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class Profil extends Fragment implements View.OnClickListener {

    ImageView profil_Toolbar;

    private static ClickButtonLisner mClickButtonLisner;
    public Profil() {
        // Required empty public constructor
    }
    public static Fragment newInstance(ClickButtonLisner listener){
        Profil fragment =  new Profil();
        mClickButtonLisner =listener;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profil, container, false);
        profil_Toolbar = (ImageView) view.findViewById(R.id.profil_Toolbar);
        profil_Toolbar.setOnClickListener(this);
        return view ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profil_Toolbar:
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



}
