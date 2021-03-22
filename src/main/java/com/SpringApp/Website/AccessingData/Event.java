package com.SpringApp.Website.AccessingData;



import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//@Data
//@SuperBuilder(toBuilder = true)
//@RequiredArgsConstructor
@MappedSuperclass
public class Event implements Serializable {

    @Column(name="name")
    protected String name;
    @Column(name="timeSlot")
    protected String timeSlot;

    protected Event(){}




    public Event(String name, String timeSlot) {
        this.name = name;
        this.timeSlot = timeSlot;




    }


    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}
    public String getTimeSlot() {
        return timeSlot;
    }
    public void setTimeSlot(String timeSlot) {this.timeSlot = timeSlot;}

}
