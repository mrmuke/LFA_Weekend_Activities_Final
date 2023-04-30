package com.SpringApp.Website.PostSchemas;

import com.SpringApp.Website.AccessingData.User;

public class Drop {
    private String sendArr;
    private String receivedArr;
    private int day;
    private int event;
    private User firstUser;
    private User secondUser;

    public Drop(){}

    public String getSendArr(){
        return this.sendArr;
    }

    public String getReceivedArr(){
        return this.receivedArr;
    }

    public int getDay(){
        return this.day;
    }

    public int getEvent(){
        return this.event;
    }

    public User getFirstUser(){
        return this.firstUser;
    }

    public User getSecondUser(){
        return this.secondUser;
    }
}
