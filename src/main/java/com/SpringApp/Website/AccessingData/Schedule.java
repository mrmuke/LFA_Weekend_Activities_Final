package com.SpringApp.Website.AccessingData;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name="date")
    private String date;
    @Column(name = "phone")
    private PhoneNumber[] phoneNumbers;
    @Column(name = "schedule")
    private ScheduleDay[] scheduleDays;
    @Column(name="displayed")
    private boolean displayed;

    protected Schedule(){}

    public Schedule(String date, PhoneNumber[] phoneNumbers, ScheduleDay[] scheduleDays)
    {
        this.date = date;
        this.phoneNumbers = phoneNumbers;
        this.scheduleDays = scheduleDays;
    }

    public boolean getDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PhoneNumber[] getPhoneNumbers(){
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumber[] phoneNumbers){
        this.phoneNumbers = phoneNumbers;
    }

    public ScheduleDay[] getScheduleDays(){
        return this.scheduleDays;
    }

    public void setScheduleDays(ScheduleDay[] scheduleDays){
        this.scheduleDays = scheduleDays;
    }
}
