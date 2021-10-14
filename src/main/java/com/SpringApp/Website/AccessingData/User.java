package com.SpringApp.Website.AccessingData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable{

    private static final long serialVersionUID = 6529685098267757690L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "emailAddress")
    private String emailAddress;
    @Column(name = "userName")
    private String userName;
    @Column(name="admin")
    private Boolean admin;
    @Column(name="picture")
    private String picture;
    @Column (name = "strikes", columnDefinition="int default 0")
    private int strikes;
    @Column (name = "test", columnDefinition="int default 3")
    private int test;

    protected User() {
    }

    public User(String emailAddress, Boolean admin, String picture,String userName, int strikes) {
        this.emailAddress = emailAddress;
        this.userName = userName;
        /////////
        this.picture=picture;

        this.admin=admin;
        this.strikes = strikes;
        this.test = 3;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, emailAddress = %s, userName='%s']",
                id, emailAddress, userName);
    }///////////////////////////////////


    public String getEmailAddress() {
        return emailAddress;
    }



    public Boolean getAdmin() {
        return admin;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPicture(){
        return picture;
    }

    public int getStrikes(){
        return strikes;
    }

    public int getTest(){
        return test;
    }

    public void setStrikes(int strikes){
        this.strikes = strikes;
    }
}
