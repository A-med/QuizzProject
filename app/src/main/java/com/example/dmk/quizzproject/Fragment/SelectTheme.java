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
public class SelectTheme extends Fragment implements View.OnClickListener {


    ImageView play_Toolbar;
    static int  lastfragment;
    private static ClickButtonPlayLisner mClickButtonPlayLisner;

    public SelectTheme() {
        // Required empty public constructor
    }
    public static Fragment newInstance(ClickButtonPlayLisner listener ){

        SelectTheme fragment =  new SelectTheme();
        mClickButtonPlayLisner =listener;
        //lastfragment = numberOfFragment;
        //Log.v("iit","play");
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.select_theme, container, false);
        play_Toolbar = (ImageView) view.findViewById(R.id.play_Toolbar);
        play_Toolbar.setOnClickListener(this);
        return view ;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play_Toolbar:
                openToolbar();
                break;

        }
    }

    void openToolbar(){

        mClickButtonPlayLisner.onFinishClickTollbarPlay();

    }



    public interface ClickButtonPlayLisner {
        public void onFinishClickTollbarPlay();

    }
}
