package com.SpringApp.Website.PostSchemas;

import com.SpringApp.Website.AccessingData.User;

public class Drop {
    private String action;
    private int day;
    private int event;
    private User first_user;
    private User second_user;

    public Drop(){}

    public String getAction(){
        return this.action;
    }

    public int getDay(){
        return this.day;
    }

    public int getEvent(){
        return this.event;
    }

    public User getFirstUser(){
        return this.first_user;
    }

    public User getSecondUser(){
        return this.second_user;
    }
}
