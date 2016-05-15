package com.example.iit.quizzproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.iit.quizzproject.core.Question;

import java.util.Vector;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 10;
    public Vector<Question> questionList = new Vector<>();
    Context context;
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
//


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }


}