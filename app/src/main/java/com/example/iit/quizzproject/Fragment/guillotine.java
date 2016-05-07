package com.example.iit.quizzproject.Fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.widget.CanaroTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class guillotine extends Fragment implements View.OnClickListener {


    CanaroTextView profile ;
    CanaroTextView play ;
    CanaroTextView settings ;
    ImageView image_toolbar;
     static int  lastfragment;
    private static ClickButtonGuillotineLisner mClickButtonGuillotineLisner;

   public guillotine() {


        // Required empty public constructor
    }
    public static Fragment newInstance(ClickButtonGuillotineLisner listener , int mlastfragment ){

        guillotine fragment =  new guillotine();
        mClickButtonGuillotineLisner =listener;
        lastfragment=mlastfragment;
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.guillotine, container, false);
        profile = (CanaroTextView) view.findViewById(R.id.profile);
        play = (CanaroTextView) view.findViewById(R.id.play);
        settings = (CanaroTextView) view.findViewById(R.id.settings);
        image_toolbar = (ImageView) view.findViewById(R.id.button_toolbar);


        if(lastfragment==1)
        {
            profile.setTextColor(getResources().getColor(R.color.selected_item_color));
        }
        else if(lastfragment==2)
        {
            play.setTextColor(getResources().getColor(R.color.selected_item_color));
        }else
        {
            settings.setTextColor(getResources().getColor(R.color.selected_item_color));
        }

        profile.setOnClickListener(this);
        play.setOnClickListener(this);
        settings.setOnClickListener(this);
        image_toolbar.setOnClickListener(this);
        return view ;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.profile:
                openProfil();
                break;
            case R.id.play:
                openPlay();
                break;
            case R.id.settings:
                openSettings();
                break;
            case R.id.button_toolbar:
                CloseGuillotine();
                break;

        }
    }

    void CloseGuillotine()
    {
        mClickButtonGuillotineLisner.onFinishClickTollbarGuillotine(this);
    }
    void openProfil(){

        mClickButtonGuillotineLisner.onFinishClickProfilGuillotine(this);

    }


    void openPlay(){
        mClickButtonGuillotineLisner.onFinishClickPlayGuillotine(this);

    }

    void openSettings(){
        mClickButtonGuillotineLisner.onFinishClickSettingGuillotine(this);

    }

    public interface ClickButtonGuillotineLisner {

        public void onFinishClickTollbarGuillotine(Fragment fragment);
        public void onFinishClickPlayGuillotine(Fragment fragment);
        public void onFinishClickSettingGuillotine(Fragment fragment);
        public void onFinishClickProfilGuillotine(Fragment fragment);

    }

}
