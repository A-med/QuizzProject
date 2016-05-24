package com.example.iit.quizzproject;

import android.content.Context;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Nabil Moalla on 24/05/2016.
 */
public class NonSwipeablePagerTabStrip extends PagerTabStrip {
    private boolean isTabSwitchEnabled;

    public NonSwipeablePagerTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isTabSwitchEnabled = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {


            return false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return false;
    }

    public void setTabSwitchEnabled(boolean isSwipeEnabled) {
        this.isTabSwitchEnabled = isSwipeEnabled;
    }
}