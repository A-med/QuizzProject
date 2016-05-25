package com.example.iit.quizzproject.fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.iit.quizzproject.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class SelectComplexity extends Fragment implements View.OnClickListener {
    Button btnHard;
    Button btnMeduim;
    Button btnEasy;
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
        btnHard = (Button) view.findViewById(R.id.buttonHard);
        btnMeduim = (Button) view.findViewById(R.id.buttonMeduim);
        btnEasy = (Button) view.findViewById(R.id.buttonEasy);
        btnHard.setOnClickListener(this);
        btnMeduim.setOnClickListener(this);
        btnEasy.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_Toolbar:
                openToolbar();
                break;
            case R.id.buttonEasy:
                openEasyComplexity("Easy");
                break;
            case R.id.buttonMeduim:
                openMediumComplexity("Medium");
                break;
            case R.id.buttonHard:
                openHardComplexity("Hard");
                break;
        }
    }
    void openToolbar() {
        mClickButtonPlayLisner.onFinishClickTollbarPlay();
    }
    void openEasyComplexity(String comp) {
        mClickButtonPlayLisner.onFinishClickEasyComplexity(comp,this);
    }
    void openMediumComplexity(String comp) {
        mClickButtonPlayLisner.onFinishClickMediumComplexity(comp,this);
    }
    void openHardComplexity(String comp) {
        mClickButtonPlayLisner.onFinishClickHardComplexity(comp,this);
    }
    public interface ClickButtonComplexityLisner {
        public void onFinishClickTollbarPlay();
        public void onFinishClickEasyComplexity(String comp,Fragment f);
        public void onFinishClickMediumComplexity(String comp,Fragment f);
        public void onFinishClickHardComplexity(String comp,Fragment f);
    }
}