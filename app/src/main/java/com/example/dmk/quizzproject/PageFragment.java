package com.example.dmk.quizzproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dmk.quizzproject.Activity.MainActivity;

/**
 * Created by Nabil Moalla on 24/04/2016.
 */
public class PageFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml



        View view = inflater.inflate(R.layout.fragment_page, container, false);
        Button  b0=(Button)view.findViewById(R.id.button);
        b0.setBackgroundColor(Color.CYAN);
        b0.setText("ddd");
        for(int i=0;i<MainActivity.questionList.size();i++){
            b0.setText(MainActivity.questionList.get(i).getProposition1());
        }
        return view;
    }
}
