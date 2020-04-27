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
    private Event[] friday;
    @Column(name="saturday")
    private Event[] saturday;
    @Column(name = "sunday")
    private Event[] sunday;

    protected Schedule(){}

    public Schedule(String date, Event[] friday, Event[] saturday, Event[] sunday)
    {
        this.date = date;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;

    }
    @Override
    public String toString() {
        return String.format(
                "Schedule[id=%d, date = %s, friday='%s',saturday = %s, sunday = %s]",
                id, date, Arrays.toString(friday), Arrays.toString(saturday), Arrays.toString(sunday));
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

    public Event[] getFriday() {
        return friday;
    }

    public void setFriday(Event[] friday) {
        this.friday = friday;
    }

    public Event[] getSaturday() {
        return saturday;
    }

    public void setSaturday(Event[] saturday) {
        this.saturday = saturday;
    }

    public Event[] getSunday() {
        return sunday;
    }

    public void setSunday(Event[] sunday) {
        this.sunday = sunday;
    }
}
