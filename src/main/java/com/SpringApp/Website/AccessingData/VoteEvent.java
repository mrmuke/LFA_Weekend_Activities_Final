package com.SpringApp.Website.AccessingData;

//import lombok.EqualsAndHashCode;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

//@SuperBuilder(toBuilder = true)
//@RequiredArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vote_events")
public class VoteEvent extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "upVotes")
    private Integer upVotes = 0;
    @Column(name = "requested")
    private User requested;
    @Column(name="description")
    private String description;

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }
    protected VoteEvent(){}
    public VoteEvent(String name, String timeSlot) {
        super(name, timeSlot);
        requested = new User();



    }
    @Override
    public String toString() {
        return String.format(
                "VoteEvent[id=%d, name = %s, timeSlot='%s']",
                id, getName(), getTimeSlot());
    }


    public Long getId() {
        return id;
    }

    public Integer getUpVotes() {
        return upVotes;
    }


    public User getRequested() {
        return requested;
    }

    public void setRequested(User requested) {
        this.requested = requested;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
