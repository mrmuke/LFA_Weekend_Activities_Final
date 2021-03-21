package com.SpringApp.Website.AccessingData;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name="date")
    private String date;
    @Column(name="friday")
    private ScheduleEvent[] friday;
    @Column(name="saturday")
    private ScheduleEvent[] saturday;
    @Column(name = "sunday")
    private ScheduleEvent[] sunday;
    @Column(name = "phone")
    private PhoneNumber[] phoneNumbers;
    @Column(name = "schedule")
    private ScheduleDay[] scheduleDays;

    protected Schedule(){}

    public Schedule(String date, ScheduleEvent[] friday, ScheduleEvent[] saturday, ScheduleEvent[] sunday, PhoneNumber[] phoneNumbers, ScheduleDay[] scheduleDays)
    {
        this.date = date;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.phoneNumbers = phoneNumbers;
        this.scheduleDays = scheduleDays;
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

    public ScheduleEvent[] getFriday() {
        return friday;
    }

    public void setFriday(ScheduleEvent[] friday) {
        this.friday = friday;
    }

    public ScheduleEvent[] getSaturday() {
        return saturday;
    }

    public void setSaturday(ScheduleEvent[] saturday) {
        this.saturday = saturday;
    }

    public ScheduleEvent[] getSunday() {
        return sunday;
    }

    public void setSunday(ScheduleEvent[] sunday) {
        this.sunday = sunday;
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
