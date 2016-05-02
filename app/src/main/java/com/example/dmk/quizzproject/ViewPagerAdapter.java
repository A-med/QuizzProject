package com.example.dmk.quizzproject;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 10;
    // Tab Titles
    private String tabtitles[] = new String[] { "1", "2", "3","4","5","6","7","8","9","10" };
    Context context;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {


            case 0:
                PageFragment fragmenttab1 = new PageFragment();
                return fragmenttab1;


            case 1:
                PageFragment fragmenttab2 = new PageFragment();
                return fragmenttab2;


            case 2:
                PageFragment fragmenttab3 = new PageFragment();
                return fragmenttab3;

            case 3:
                PageFragment fragmenttab4 = new PageFragment();
                return fragmenttab4;


            case 4:
                PageFragment fragmenttab5 = new PageFragment();
                return fragmenttab5;


            case 5:
                PageFragment fragmenttab6 = new PageFragment();
                return fragmenttab6;


            case 6:
                PageFragment fragmenttab7 = new PageFragment();
                return fragmenttab7;


            case 7:
                PageFragment fragmenttab8 = new PageFragment();
                return fragmenttab8;


            case 8:
                PageFragment fragmenttab9 = new PageFragment();
                return fragmenttab9;


            case 9:
                PageFragment fragmenttab10 = new PageFragment();
                return fragmenttab10;

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}