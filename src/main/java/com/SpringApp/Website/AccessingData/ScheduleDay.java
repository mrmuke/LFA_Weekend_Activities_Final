package com.SpringApp.Website.AccessingData;

import java.io.Serializable;

public class ScheduleDay implements Serializable{
    private String date;
    private ScheduleEvent[] events;
    
    protected ScheduleDay(){}

    public ScheduleDay(String date, ScheduleEvent[] events){
        this.date = date;
        this.events = events;
    }

    public String getDate(){
        return this.date;
    }

    public ScheduleEvent[] getEvents(){
        return this.events;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setEvents(ScheduleEvent[] events){
        this.events = events;
    }
}