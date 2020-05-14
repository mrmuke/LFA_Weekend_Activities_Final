package com.SpringApp.Website.AccessingData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "events")
public class Event implements Serializable {
    private static final long serialVersionUID =2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "timeSlot")
    private String timeSlot;
    @Column(name = "upVotes")
    private Integer upVotes = 0;
    @Column(name="signUp")
    private Boolean signUp = false;
    @Column(name = "usersSignedUp")
    private User[] usersSignedUp;
    @Column(name = "personLimit")
    private Integer personLimit = 0;




    protected Event() {}

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }

    public Event(String name, String timeSlot) {
        this.name = name;
        this.timeSlot = timeSlot;
        usersSignedUp = new User[0];



    }
    @Override
    public String toString() {
        return String.format(
                "Event[id=%d, upVotes = %d,  name='%s', timeSlot='%s', signUp = '%B']",
                id, upVotes, name, timeSlot, signUp);
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

    public Boolean getSignUp() {
        return signUp;
    }

    public void setSignUp(Boolean signUp) {
        this.signUp = signUp;
    }

    public User[] getUsersSignedUp() {
        return usersSignedUp;
    }

    public void setUsersSignedUp(User[] usersSignedUp) {
        this.usersSignedUp = usersSignedUp;
    }

    public Integer getPersonLimit() {
        return personLimit;
    }

    public void setPersonLimit(Integer personLimit) {
        this.personLimit = personLimit;
    }
}
