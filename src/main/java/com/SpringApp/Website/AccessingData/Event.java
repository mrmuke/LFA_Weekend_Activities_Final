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
    @Column(name = "upVotes")
    private Integer upVotes = 0;

    @Column(name = "upVoted")
    private Boolean upVoted;




    protected Event() {}

    public Event(String name, String timeSlot) {
        this.name = name;
        this.timeSlot = timeSlot;
        upVoted = false;



    }
    @Override
    public String toString() {
        return String.format(
                "Event[id=%d, upVotes = %d, upVoted = %b, name='%s', timeSlot='%s']",
                id, upVotes, upVoted, name, timeSlot);
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

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }
    public boolean isUpVoted() {
        return upVoted;
    }

    public void setUpVoted(Boolean upVoted) {
        this.upVoted = upVoted;
    }
    public Boolean getUpVoted()
    {
        return upVoted;
    }

}
