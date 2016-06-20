package com.example.iit.quizzproject.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.Person;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Score extends Fragment  implements View.OnClickListener {

    TextView textOver;
    TextView textScore;
    Button facebook;
    Button replay;
    Button close;
    static int point;
    static boolean etat;
    private static ClickButtonLisner mClickButtonLisner;
    public Score() {
        // Required empty public constructor
    }

    public static Fragment newInstance(ClickButtonLisner listener, int p, boolean b){
        Score fragment =  new Score();
        point=p;
        etat=b;
        mClickButtonLisner =listener;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        textOver=(TextView)view.findViewById(R.id.finish_text);
        textScore=(TextView)view.findViewById(R.id.score);
        facebook=(Button)view.findViewById(R.id.button_share);
        replay=(Button)view.findViewById(R.id.button_replay);
        close=(Button)view.findViewById(R.id.button_close);
        facebook.setOnClickListener(this);
        replay.setOnClickListener(this);
        close.setOnClickListener(this);
        textScore.setText(point+" pts");
        if(etat==true)
            textOver.setText("Game is Finish !");

        // Inflate the layout for this fragment
        return view;




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_share:
                shareInFacebook();
                break;
            case R.id.button_replay:
                replayGame();
                break;
            case R.id.button_close:
                closeGame();
                break;
            
        }

    }

    private void closeGame() {
        mClickButtonLisner.closeGame();
    }

    private void replayGame() {
        mClickButtonLisner.replayGame();
    }

    private void shareInFacebook() {
    }

    public interface ClickButtonLisner {
        public void closeGame();
        public void replayGame();


    }

}
