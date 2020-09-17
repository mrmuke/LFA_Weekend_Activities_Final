package com.SpringApp.Website.AccessingData;

import javax.persistence.*;

@Entity
@Table(name = "schedule_events")
public class ScheduleEvent extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="signUp")
    private Boolean signUp = false;
    @Column(name = "usersSignedUp")
    private User[] usersSignedUp;
    @Column(name = "personLimit")
    private Integer personLimit = 0;


    public ScheduleEvent(String name, String timeSlot) {
        super(name, timeSlot);
        usersSignedUp = new User[0];




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
