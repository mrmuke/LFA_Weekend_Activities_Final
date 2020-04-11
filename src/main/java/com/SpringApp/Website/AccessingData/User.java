package com.SpringApp.Website.AccessingData;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "emailAddress")
    private String emailAddress;
    @Column(name = "userName")
    private String userName;
    ////////////
    @Column(name = "upvotes")
    private Event[] upvotes;


    protected User() {
    }

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
        String[] words = emailAddress.split("\\.");
        this.userName = words[0];
        /////////
        upvotes = new Event[0];


    }
    @Override
    public String toString() {
        return String.format(
                "User[id=%d, emailAddress = %s, userName='%s']",
                id, emailAddress, userName);
    }///////////////////////////////////

    public Long getId() {
        return id;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    public Event[] getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Event[] upvotes) {
        this.upvotes = upvotes;
    }
}
