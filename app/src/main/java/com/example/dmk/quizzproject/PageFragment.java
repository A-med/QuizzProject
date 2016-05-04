package com.example.dmk.quizzproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dmk.quizzproject.Activity.MainActivity;
import com.example.dmk.quizzproject.core.Person;

import java.util.ArrayList;

/**
 * Created by Nabil Moalla on 24/04/2016.
 */
public class PageFragment extends Fragment {

    public static PageFragment newInstance( ) {
        PageFragment fragment = new PageFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml



        View view = inflater.inflate(R.layout.fragment_page, container, false);
       Button  b0=(Button)view.findViewById(R.id.button);
   b0.setBackgroundColor(Color.CYAN);
        Button  b1=(Button)view.findViewById(R.id.button2);  Button  b2=(Button)view.findViewById(R.id.button3);

        b1.setBackgroundColor(Color.CYAN);
      TextView  question=(TextView)view.findViewById(R.id.question_txt);
        b2.setBackgroundColor(Color.CYAN);
        //b0.setText("ddd");
        for(int i=0;i<MainActivity.questionList.size();i++){
            b0.setText(MainActivity.questionList.get(i).getProposition1());
            b1.setText(MainActivity.questionList.get(i).getProposition2());
            b2.setText(MainActivity.questionList.get(i).getProposition3());
            question.setText(MainActivity.questionList.get(i).getText_question());



        }
        return view;
    }
}
