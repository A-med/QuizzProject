package com.example.iit.quizzproject;

/**
 * Created by hamdy on 23/05/16.
 */
public class FriendsListItemWarpper {
    private User mUser;
    private boolean isChecked;

    public FriendsListItemWarpper(User user, boolean checked) {
        mUser = user;
        isChecked = checked;
    }


    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
