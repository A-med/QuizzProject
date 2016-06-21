package com.example.iit.quizzproject.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.iit.quizzproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements View.OnClickListener {

    ImageView settings_Toolbar;
    Switch sMusique;
    Switch sVibration;
    private static ClickButtonLisner mClickButtonLisner;
    static int musique;
    static int vibration;
    public Settings() {
        // Required empty public constructor
    }
    public static Fragment newInstance(ClickButtonLisner listener, int m, int v){
        Settings fragment =  new Settings();
        musique=m;
        vibration=v;
        mClickButtonLisner =listener;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings, container, false);
        settings_Toolbar = (ImageView) view.findViewById(R.id.settings_Toolbar);
        settings_Toolbar.setOnClickListener(this);
        sMusique=(Switch) view.findViewById(R.id.music);
        sMusique.setOnClickListener(this);
        sVibration=(Switch) view.findViewById(R.id.vibration);
        sVibration.setOnClickListener(this);
        if(musique==1)
            sMusique.setChecked(true);
        if(vibration==1)
            sVibration.setChecked(true);

        return view ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_Toolbar:
                openToolbar();
                break;
            case R.id.music:
                buttonMuscCaled();
                break;
            case R.id.vibration:
                buttonVibratioCaled();
                break;

        }
    }

    private void buttonVibratioCaled() {

        if(sVibration.isChecked())
        {
            mClickButtonLisner.changeStateVibration(true);
        }else {
            mClickButtonLisner.changeStateVibration(false);
        }
    }

    private void buttonMuscCaled() {
        if(sMusique.isChecked())
        {
            mClickButtonLisner.changeStateMusique(true);
        }else {
            mClickButtonLisner.changeStateMusique(false);
        }

    }

    void openToolbar(){

        mClickButtonLisner.onFinishClickButtonSettings();

    }

    public interface ClickButtonLisner {
        public void onFinishClickButtonSettings();
        public void changeStateMusique(boolean b);
        public void changeStateVibration(boolean b);


    }



}
