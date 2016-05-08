package com.example.iit.quizzproject.core;

/**
 * Created by dmk on 30/04/16.
 */

import java.io.Serializable;


public class Person implements Serializable {
    private String mName;
    private int mRank;


    private int mImg;

    public Person(String name, int rank, int img) {
        mName = name;
        mRank = rank;
        mImg = img;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getRank() {
        return mRank;
    }

    public void setRank(int rank) {
        this.mRank = rank;
    }

    public int getmImg() {
        return mImg;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }
}
