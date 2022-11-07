package com.SpringApp.Website.AccessingData;

import java.io.Serializable;

public class UserDate implements Serializable{
    private static final long serialVersionUID = 6529685267757690L;
    String dateString;
    String userName;

    public UserDate(String dateString, String userName) {
       this.dateString = dateString;
       this.userName=userName;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    protected UserDate(){
    }
}
