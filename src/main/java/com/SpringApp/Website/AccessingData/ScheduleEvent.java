package com.SpringApp.Website.AccessingData;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void addUsersSignedUp(User user){
        ArrayList<User> al = new ArrayList<User>(Arrays.asList(this.usersSignedUp));
        if(al.size() < this.personLimit){
            al.add(user);
            while(al.remove(null)){}
            this.usersSignedUp = al.toArray(new User[al.size()]);
        } else {
            addWaitlist(user);
        }
    }

    public void addWaitlist(User user){
        ArrayList<User> al = new ArrayList<User>(Arrays.asList(this.waitlist));
        al.add(user);
        while(al.remove(null)){}
        this.waitlist = al.toArray(new User[al.size()]);
    }

    public void removeUser(User user){
        ArrayList<User> al = new ArrayList<User>(Arrays.asList(this.usersSignedUp));
        ArrayList<User> wl = new ArrayList<User>(Arrays.asList(this.waitlist));

        while(al.remove(null)){}
        for(int i = 0; i < al.size(); i++){
            User curUser = al.get(i);
            if(curUser == null){
                continue;
            }
            if(curUser.getEmailAddress().equals(user.getEmailAddress())){
                al.remove(i);
                break;
            }
        }
        if(wl.size() > 0){
            al.add(wl.get(0));
            wl.remove(0);
        }
        this.usersSignedUp = al.toArray(new User[al.size()]);

        while(wl.remove(null)){}
        for(int i = 0; i < wl.size(); i++){
            User curUser = wl.get(i);
            if(curUser == null){
                continue;
            }
            if(curUser.getEmailAddress().equals(user.getEmailAddress())){
                wl.remove(i);
                break;
            }
        }
        this.waitlist = wl.toArray(new User[wl.size()]);
    }

    public void bumpUser(User user){
        int index = -1;
        ArrayList<User> al = new ArrayList<User>(Arrays.asList(this.usersSignedUp));
        ArrayList<User> wl = new ArrayList<User>(Arrays.asList(this.waitlist));

        for(int i = 0; i < this.usersSignedUp.length; i++){
            if(this.usersSignedUp[i].getEmailAddress().equals(user.getEmailAddress())){
                index = i;
                break;
            }
        }
        if(index == -1){
            return;
        }
        al.remove(index);

        if(wl.size() > 0){
            al.add(wl.get(0));
            wl.remove(0);
        }
        
        wl.add(user);

        this.waitlist = wl.toArray(new User[wl.size()]);
        this.usersSignedUp = al.toArray(new User[al.size()]);
    }

    public void swapUser(String action, User first_user, User second_user){
        ArrayList<User> receiveArr;
        ArrayList<User> sendArr;

        if(action.equals("waitlist")){
            receiveArr = new ArrayList<User>(Arrays.asList(this.waitlist));
            sendArr = new ArrayList<User>(Arrays.asList(this.usersSignedUp));
        } else {
            receiveArr = new ArrayList<User>(Arrays.asList(this.usersSignedUp));
            sendArr = new ArrayList<User>(Arrays.asList(this.waitlist));
        }
    }
}
