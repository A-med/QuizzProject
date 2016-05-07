package com.example.dmk.quizzproject;


import java.io.Serializable;

/**
 * Created by hamdy on 23/04/16.
 */
public class User implements Serializable{
    String name;
    String age;
    int photoId;
    private boolean selected;

    public User(String name, String age, int photoId , boolean selected){
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge(){
        return age;
    }
    public void setAge(String age)
    {
        this.age=age;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getPhotoId() {

        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }






}
