package com.SpringApp.Website.PostSchemas;

import com.SpringApp.Website.AccessingData.User;

public class EditUsersSignedUp {
    private String action;
    private int day;
    private int event;
    private User user;

    public EditUsersSignedUp(){}

    public String getAction(){
        return this.action;
    }

    public int getDay(){
        return this.day;
    }

    public int getEvent(){
        return this.event;
    }

    public User getUser(){
        return this.user;
    }
}
