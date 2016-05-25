package com.example.iit.quizzproject.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.iit.quizzproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTheme extends Fragment implements View.OnClickListener {


    static int lastfragment;
    private static ClickButtonThemeLisner mClickButtonThemeLisner;
    ImageView play_Toolbar;
    AppCompatButton randomButton;
    AppCompatButton sportButton;
    AppCompatButton matimaticalButton;
    AppCompatButton reflexionButton;
    AppCompatButton algorithmicButton;
    AppCompatButton historyButton;
    AppCompatButton generalCultureButton;
    AppCompatButton healthButton;
    AppCompatButton cinemaButton;
    AppCompatButton policyButton;

    public SelectTheme() {
        // Required empty public constructor
    }

    public static Fragment newInstance(ClickButtonThemeLisner listener) {

        SelectTheme fragment = new SelectTheme();
        mClickButtonThemeLisner = listener;
        //lastfragment = numberOfFragment;
        //Log.v("iit","play");
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.select_theme, container, false);
        play_Toolbar = (ImageView) view.findViewById(R.id.play_Toolbar);
        play_Toolbar.setOnClickListener(this);

        randomButton = (AppCompatButton) view.findViewById(R.id.buttonRandom);
        sportButton = (AppCompatButton) view.findViewById(R.id.buttonSport);
        matimaticalButton = (AppCompatButton) view.findViewById(R.id.buttonMathematical);
        reflexionButton = (AppCompatButton) view.findViewById(R.id.buttonReflexion);
        algorithmicButton = (AppCompatButton) view.findViewById(R.id.buttonAlgorithmic);
        historyButton = (AppCompatButton) view.findViewById(R.id.buttonHistory);
        generalCultureButton = (AppCompatButton) view.findViewById(R.id.buttonGeneral_culture);
        healthButton = (AppCompatButton) view.findViewById(R.id.buttonHealth);
        cinemaButton = (AppCompatButton) view.findViewById(R.id.buttonCinema);
        policyButton = (AppCompatButton) view.findViewById(R.id.buttonPolicy);

        randomButton.setOnClickListener(this);
        sportButton.setOnClickListener(this);
        matimaticalButton.setOnClickListener(this);
        reflexionButton.setOnClickListener(this);
        algorithmicButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        generalCultureButton.setOnClickListener(this);
        healthButton.setOnClickListener(this);
        cinemaButton.setOnClickListener(this);
        policyButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play_Toolbar:
                openToolbar();
                break;
            case R.id.buttonRandom:
                selectedTheme(randomButton);
                openComplexity("random");
                break;
            case R.id.buttonSport:
                selectedTheme(sportButton);
                openComplexity("sport");
                break;
            case R.id.buttonMathematical:
                selectedTheme(matimaticalButton);
                openComplexity("math");
                break;
            case R.id.buttonReflexion:
                selectedTheme(reflexionButton);
                openComplexity("reflexion");
                break;
            case R.id.buttonAlgorithmic:
                selectedTheme(algorithmicButton);
                openComplexity("alogorithmique");
                break;
            case R.id.buttonHistory:
                selectedTheme(historyButton);
                openComplexity("history");
                break;
            case R.id.buttonGeneral_culture:
                selectedTheme(generalCultureButton);
                openComplexity("genetalculture");
                break;
            case R.id.buttonHealth:
                selectedTheme(healthButton);
                openComplexity("health");
                break;
            case R.id.buttonCinema:
                selectedTheme(cinemaButton);
                openComplexity("cinema");
                break;
            case R.id.buttonPolicy:
                selectedTheme(policyButton);
                openComplexity("policy");
                break;


        }
    }

    void selectedTheme(AppCompatButton button)
    {
        button.setTextColor(getResources().getColor(R.color.selected_item_color));
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.selected_shape));
    }

    void openComplexity(String theme) {
        play_Toolbar.setOnClickListener(null);
        randomButton.setOnClickListener(null);
        sportButton.setOnClickListener(null);
        matimaticalButton.setOnClickListener(null);
        reflexionButton.setOnClickListener(null);
        algorithmicButton.setOnClickListener(null);
        historyButton.setOnClickListener(null);
        generalCultureButton.setOnClickListener(null);
        healthButton.setOnClickListener(null);
        cinemaButton.setOnClickListener(null);
        policyButton.setOnClickListener(null);
        mClickButtonThemeLisner.onFinishChoiceTheme(theme);

    }

    void openToolbar() {

        mClickButtonThemeLisner.onFinishClickTollbarPlay();

    }


    public interface ClickButtonThemeLisner {
        public void onFinishClickTollbarPlay();
        public void onFinishChoiceTheme(String theme);

    }
}
