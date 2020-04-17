package com.SpringApp.Website.AccessingData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "events")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "timeSlot")
    private String timeSlot;
    @Column(name = "upVotes")
    private Integer upVotes = 0;




    protected Event() {}

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }

    public Event(String name, String timeSlot) {
        this.name = name;
        this.timeSlot = timeSlot;



    }
    @Override
    public String toString() {
        return String.format(
                "Event[id=%d, upVotes = %d,  name='%s', timeSlot='%s']",
                id, upVotes, name, timeSlot);
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}
    public String getTimeSlot() {
        return timeSlot;
    }
    public void setTimeSlot(String timeSlot) {this.timeSlot = timeSlot;}

    public Integer getUpVotes() {
        return upVotes;
    }


}
