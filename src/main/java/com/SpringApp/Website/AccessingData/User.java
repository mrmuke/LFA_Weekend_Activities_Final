package com.SpringApp.Website.AccessingData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
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

    protected User() {
    }

    public User(String emailAddress, Boolean admin, String picture,String userName) {
        this.emailAddress = emailAddress;
        this.userName = userName;
        /////////
        this.picture=picture;

        this.admin=admin;



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


}
