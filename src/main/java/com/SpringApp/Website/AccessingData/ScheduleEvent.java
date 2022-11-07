package com.SpringApp.Website.AccessingData;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "schedule_events")
public class ScheduleEvent extends Event implements Serializable {
    private static final long serialVersionUID = -3588755195428018248L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="signUp")
    private Boolean signUp = false;

    @Column(name = "usersSignedUp")
    private User[] usersSignedUp;
    @Column(name = "unsignedUp")
    private UserDate[] unsignedUp;
    @Column(name = "waitlist")
    private User[] waitlist;
    @Column(name = "personLimit")
    private Integer personLimit = 0;



    public ScheduleEvent(String name, String timeSlot) {
        super(name, timeSlot);
        usersSignedUp = new User[0];
        waitlist= new User[0];
        unsignedUp=new UserDate[0];
    }

    public User[] getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(User[] waitlist) {
        this.waitlist = waitlist;
    }

    public UserDate[] getUnsignedUp() {
        return unsignedUp;
    }

    public void setUnsignedUp(UserDate[] unsignedUp) {
        this.unsignedUp = unsignedUp;
    }

    public Long getId() {
        return id;
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
