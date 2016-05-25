package com.example.iit.quizzproject;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
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
    Vibrator vibrator;
    MediaPlayer mediaPlayer;

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


    public void killButtons() {
        prop1.setClickable(false);
        prop2.setClickable(false);
        prop3.setClickable(false);
    }

    public void validateAnswer(Button b1, Button b2, Button b3) {

        if (mQuestion.getAnswer().equals(b1.getText())) {
            b1.setBackgroundColor(getResources().getColor(R.color.progress_color));
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.win);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    mp = null;

                }
            });


        } else {
            b1.setBackgroundColor(getResources().getColor(R.color.mistake_color));

            vibrator = (Vibrator) this.getContext().getSystemService(Context.VIBRATOR_SERVICE);                 //   v.vibrate(500);Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.wrong);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    mp = null;

                }
            });

            if (mQuestion.getAnswer().equals(b2.getText())) {
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
                killButtons();
                validateAnswer(prop1, prop2, prop3);
                break;

            case (R.id.button2): {
                killButtons();
                validateAnswer(prop2, prop1, prop3);
                break;

            }
            case (R.id.button3):
                killButtons();
                validateAnswer(prop3, prop2, prop1);
                break;
        }


    }
}
