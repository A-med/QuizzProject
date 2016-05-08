package com.example.iit.quizzproject.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iit.quizzproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Game extends Fragment implements View.OnClickListener {


    private static ClickButtonLisner mClickButtonLisner;

    public Game() {
        // Required empty public constructor
    }

    public static Fragment newInstance(ClickButtonLisner listener) {
        Game fragment = new Game();
        mClickButtonLisner = listener;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_Toolbar:

                break;

        }
    }


    public interface ClickButtonLisner {
        public void onFinishClickButtonSettings();

    }


}
