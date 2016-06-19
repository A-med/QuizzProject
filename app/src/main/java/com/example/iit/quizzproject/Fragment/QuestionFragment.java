package com.example.iit.quizzproject.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.iit.quizzproject.activity.QuestionActivity;
import com.example.iit.quizzproject.R;
import com.example.iit.quizzproject.core.QuestionSqlite;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener {


    static QuestionSqlite mQuestion;

    TextView question;
    Button prop1;
    Button prop2;
    Button prop3;
    Button nextQuestion;

    View view ;


    private static ClickButtonLisner mClickButtonLisner;
    public static QuestionFragment newInstance(ClickButtonLisner listener,QuestionSqlite q) {
        QuestionFragment fragment = new QuestionFragment();
        mClickButtonLisner= listener;
        mQuestion = q;

        /*Bundle args = new Bundle();

        args.putSerializable("question", q);
        fragment.setArguments(args);*/

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_question, container, false);


        question = (TextView) view.findViewById(R.id.question_txt);
        prop1 = (Button) view.findViewById(R.id.button);

        prop2 = (Button) view.findViewById(R.id.button2);
        prop3 = (Button) view.findViewById(R.id.button3);
        nextQuestion = (Button) view.findViewById(R.id.Next);
        nextQuestion.setVisibility(view.GONE);
        prop1.setOnClickListener(this);
        nextQuestion.setOnClickListener(this);
        prop2.setOnClickListener(this);
        prop3.setOnClickListener(this);



        onChangeLangue(QuestionActivity.idLangue);



        return view;
    }


    public void killButtons() {
        prop1.setClickable(false);
        prop2.setClickable(false);
        prop3.setClickable(false);
        if(QuestionActivity.indexQuestion!=9)
            nextQuestion.setVisibility(view.VISIBLE);
        else
            mClickButtonLisner.endQuestion();



    }

    public void validateAnswer(Button b1, Button b2, Button b3) {

       if (mQuestion.getAnswerFr().equals(b1.getText())) {
            b1.setBackgroundColor(getResources().getColor(R.color.progress_color));
           mClickButtonLisner.onClickButtonChoiceRepance(true);
        } else {
            b1.setBackgroundColor(getResources().getColor(R.color.mistake_color));
           mClickButtonLisner.onClickButtonChoiceRepance(false);
            if (mQuestion.getAnswerFr().equals(b2.getText())) {
                b2.setBackgroundColor(getResources().getColor(R.color.progress_color));

            } else {
                b3.setBackgroundColor(getResources().getColor(R.color.progress_color));

            }
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.button):

                validateAnswer(prop1, prop2, prop3);
                killButtons();

                break;

            case (R.id.button2):

                validateAnswer(prop2, prop1, prop3);
                killButtons();
                break;


            case (R.id.button3):

                validateAnswer(prop3, prop2, prop1);
                killButtons();
                break;
            case (R.id.Next):
                mClickButtonLisner.onClickNextQuestion();
                break;
        }



    }




    public void onChangeLangue(int i) {
        if(i==0) {
            question.setText(mQuestion.getTextQuestionFr());
            prop1.setText(mQuestion.getProposition1Fr());
            prop2.setText(mQuestion.getProposition2Fr());
            prop3.setText(mQuestion.getProposition3Fr());
        }
        else
        {
            question.setText(mQuestion.getTextQuestionAn());
            prop1.setText(mQuestion.getProposition1An());
            prop2.setText(mQuestion.getProposition2An());
            prop3.setText(mQuestion.getProposition3An());
        }
    }

    public interface ClickButtonLisner {
        public void onClickButtonChoiceRepance(Boolean rep );
        public void onClickNextQuestion();
        public void endQuestion();

    }

}
