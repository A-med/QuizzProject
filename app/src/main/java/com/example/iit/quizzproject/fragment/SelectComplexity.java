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
public class SelectComplexity extends Fragment implements View.OnClickListener {


    private static ClickButtonComplexityLisner mClickButtonPlayLisner;
    ImageView play_Toolbar;

    public SelectComplexity() {
        // Required empty public constructor
    }

    public static Fragment newInstance(ClickButtonComplexityLisner listener) {

        SelectComplexity fragment = new SelectComplexity();
        mClickButtonPlayLisner = listener;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.select_complexity, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play_Toolbar:
                openToolbar();
                break;

        }
    }

    void openToolbar() {

        mClickButtonPlayLisner.onFinishClickTollbarPlay();

    }


    public interface ClickButtonComplexityLisner {
        public void onFinishClickTollbarPlay();

    }
}
