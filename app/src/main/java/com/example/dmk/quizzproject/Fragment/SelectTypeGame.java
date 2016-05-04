package com.example.dmk.quizzproject.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dmk.quizzproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTypeGame extends Fragment implements View.OnClickListener {


    AppCompatButton monoPlayer;
    AppCompatButton multiPlayer;

    private static ClickButtonTypeGameLisner mClickButtonTypeGameLisner;

    public SelectTypeGame() {
        // Required empty public constructor
    }
    public static Fragment newInstance(ClickButtonTypeGameLisner listener ){

        SelectTypeGame fragment =  new SelectTypeGame();
        mClickButtonTypeGameLisner =listener;

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.select_type_game, container, false);
        monoPlayer = (AppCompatButton) view.findViewById(R.id.buttonMonoPlayer);
        multiPlayer = (AppCompatButton) view.findViewById(R.id.buttonMultiPlayer);
        monoPlayer.setOnClickListener(this);
        multiPlayer.setOnClickListener(this);

        return view ;
    }

    @Override
    public void onClick(View v) {


            switch (v.getId()) {
                case R.id.buttonMonoPlayer:
                    openComplexity("mono");
                    break;
                case R.id.buttonMultiPlayer:
                    openComplexity("multi");
                    break;


        }
    }

    void openComplexity(String type){

        mClickButtonTypeGameLisner.onFinishSelectTypeGame(type,this);

    }



    public interface ClickButtonTypeGameLisner {
        public void onFinishSelectTypeGame(String type, Fragment f);

    }
}
