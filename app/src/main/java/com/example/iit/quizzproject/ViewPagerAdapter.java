package com.example.iit.quizzproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iit.quizzproject.core.Question;

import java.util.Vector;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 10;

    Context context;
    public Vector<Question> questionList = new Vector<>();
    // Tab Titles
    private String tabtitles[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public ViewPagerAdapter(FragmentManager fm,Vector question) {

        super(fm);
        questionList=question;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {



        return PageFragment.newInstance(questionList.elementAt(position));
//        switch (position) {
//
//
//            case 0:
//                PageFragment fragmenttab1 = new PageFragment();
//                Button b0 = (Button) fragmenttab1.getActivity().findViewById(com.example.iit.quizzproject.R.id.button);
//                b0.setBackgroundColor(Color.RED);
//                return fragmenttab1;
//
//
//            case 1:
//                PageFragment fragmenttab2 = new PageFragment();
//                return fragmenttab2;
//
//
//            case 2:
//                PageFragment fragmenttab3 = new PageFragment();
//                return fragmenttab3;
//
//            case 3:
//                PageFragment fragmenttab4 = new PageFragment();
//                return fragmenttab4;
//
//
//            case 4:
//                PageFragment fragmenttab5 = new PageFragment();
//                return fragmenttab5;
//
//
//            case 5:
//                PageFragment fragmenttab6 = new PageFragment();
//                return fragmenttab6;
//
//
//            case 6:
//                PageFragment fragmenttab7 = new PageFragment();
//                return fragmenttab7;
//
//
//            case 7:
//                PageFragment fragmenttab8 = new PageFragment();
//                return fragmenttab8;
//
//
//            case 8:
//                PageFragment fragmenttab9 = new PageFragment();
//                return fragmenttab9;
//
//
//            case 9:
//                PageFragment fragmenttab10 = new PageFragment();
//                return fragmenttab10;
//
//        }
//        return null;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }


}