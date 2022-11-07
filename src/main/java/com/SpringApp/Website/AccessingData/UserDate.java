package com.SpringApp.Website.AccessingData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_date")
public class UserDate implements Serializable{

    private static final long serialVersionUID = -123373483;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_string")
    String dateString;
    @Column(name = "user_name")
    String userName;
    
    protected UserDate() {
    }

    public UserDate(String datestring, String username) {
       this.dateString = datestring;
       this.userName=username;
    }

   
}
