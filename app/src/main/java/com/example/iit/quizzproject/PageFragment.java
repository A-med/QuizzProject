package com.example.iit.quizzproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.iit.quizzproject.core.Question;


/**
 * Created by Nabil Moalla on 24/04/2016.
 */
public class PageFragment extends Fragment implements View.OnClickListener {

    Question mQuestion;

    TextView question;
    Button prop1;
    Button prop2;
    Button prop3;


    public static PageFragment newInstance(Question q) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();

        args.putSerializable("question", q);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestion = (Question) getArguments().getSerializable("question");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(com.example.iit.quizzproject.R.layout.fragment_page, container, false);

        question = (TextView) view.findViewById(R.id.question_txt);
        prop1 = (Button) view.findViewById(com.example.iit.quizzproject.R.id.button);

        prop2 = (Button) view.findViewById(com.example.iit.quizzproject.R.id.button2);
        prop3 = (Button) view.findViewById(com.example.iit.quizzproject.R.id.button3);
        prop1.setOnClickListener(this);
        prop2.setOnClickListener(this);
        prop3.setOnClickListener(this);
        question.setText(mQuestion.getText_question());
        prop1.setText(mQuestion.getProposition1());
        prop2.setText(mQuestion.getProposition2());
        prop3.setText(mQuestion.getProposition3());

        return view;
    }


    public void killButtons()
    {    prop1.setClickable(false);
        prop2.setClickable(false);
        prop3.setClickable(false);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.button):
                    killButtons();
                if (mQuestion.getAnswer().equals(prop1.getText())) {
                    prop1.setBackgroundColor(getResources().getColor(R.color.progress_color));
                } else {
                    prop1.setBackgroundColor(getResources().getColor(R.color.mistake_color));
                    if (mQuestion.getAnswer().equals(prop2.getText())) {
                        prop2.setBackgroundColor(getResources().getColor(R.color.progress_color));
                    } else {
                        prop3.setBackgroundColor(getResources().getColor(R.color.progress_color));
                    }
                }
                break;
            case (R.id.button2):
                killButtons();
                if (mQuestion.getAnswer().equals(prop2.getText())) {
                    prop2.setBackgroundColor(getResources().getColor(R.color.progress_color));
                } else {
                    prop2.setBackgroundColor(getResources().getColor(R.color.mistake_color));
                    if (mQuestion.getAnswer().equals(prop1.getText())) {
                        prop1.setBackgroundColor(getResources().getColor(R.color.progress_color));
                    } else {
                        prop3.setBackgroundColor(getResources().getColor(R.color.progress_color));
                    }
                }
                break;
            case (R.id.button3):
                killButtons();
                if (mQuestion.getAnswer().equals(prop3.getText())) {
                    prop3.setBackgroundColor(getResources().getColor(R.color.progress_color));
                } else {
                    prop3.setBackgroundColor(getResources().getColor(R.color.mistake_color));
                    if (mQuestion.getAnswer().equals(prop2.getText())) {
                        prop2.setBackgroundColor(getResources().getColor(R.color.progress_color));
                    } else {
                        prop1.setBackgroundColor(getResources().getColor(R.color.progress_color));
                    }
                }
                break;
        }


    }
}
