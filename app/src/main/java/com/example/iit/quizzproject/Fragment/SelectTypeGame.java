package com.example.iit.quizzproject.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iit.quizzproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTypeGame extends Fragment implements View.OnClickListener {


    Button monoPlayer;
    Button multiPlayer;
    ImageView close;

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
        monoPlayer = (Button) view.findViewById(R.id.buttonMonoPlayer);
        multiPlayer = (Button) view.findViewById(R.id.buttonMultiPlayer);
        close =(ImageView)view.findViewById(R.id.button_close);
        close.setOnClickListener(this);
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
                case R.id.button_close:
                    closeTypeGame();
                    break;


        }
    }

    private void closeTypeGame() {
        mClickButtonTypeGameLisner.onClickCloseTypeGame(this);

    }

    void openComplexity(String type){

        mClickButtonTypeGameLisner.onFinishSelectTypeGame(type,this);

    }



    public interface ClickButtonTypeGameLisner {
        public void onFinishSelectTypeGame(String type, Fragment f);
        public void onClickCloseTypeGame(Fragment f);

    }
}
