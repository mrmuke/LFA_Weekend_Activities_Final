package com.SpringApp.Website.AccessingData;

import java.io.Serializable;

public class PhoneNumber implements Serializable {
    private String dorm;
    private String teacherName;
    private String phoneNumber;

    protected PhoneNumber(){}

    public PhoneNumber(String dorm, String teacherName, String phoneNumber){
        this.dorm = dorm;
        this.teacherName = teacherName;
        this.phoneNumber = phoneNumber;
    }

    public String getDorm(){
        return this.dorm;
    }

    public void setDorm(String dorm){
        this.dorm = dorm;
    }

    public String getTeacherName(){
        return this.teacherName;
    }

    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
