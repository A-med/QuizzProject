package com.example.iit.quizzproject.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iit.quizzproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements View.OnClickListener {

    ImageView settings_Toolbar;

    private static ClickButtonLisner mClickButtonLisner;
    public Settings() {
        // Required empty public constructor
    }
    public static Fragment newInstance(ClickButtonLisner listener){
        Settings fragment =  new Settings();
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

        return view ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_Toolbar:
                openToolbar();
                break;

        }
    }

    void openToolbar(){

        mClickButtonLisner.onFinishClickButtonSettings();

    }

    public interface ClickButtonLisner {
        public void onFinishClickButtonSettings();

    }



}
