package com.SpringApp.Website.AccessingData;

import javax.persistence.*;


@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "timeSlot")
    private String timeSlot;
    @Column(columnDefinition = "integer default 0")
    private Integer upVotes;


    protected Event() {}

    public Event(String name, String timeSlot) {
        this.name = name;
        this.timeSlot = timeSlot;


    }
    @Override
    public String toString() {
        return String.format(
                "Event[id=%d, upVotes = %d, name='%s', timeSlot='%s']",
                id, name, timeSlot, upVotes);
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

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }
}
