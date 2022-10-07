package com.SpringApp.Website.AccessingData;
import javax.persistence.*;

@Entity
@Table(name = "vote_events")
public class VoteEvent extends Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "requested")
    private User requested;
    @Column(name="description")
    private String description;
    @Column(name = "upvotes")
    private String[] upvotes  = new String[0];

    protected VoteEvent(){}

    public String[] getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(String[] upvotes) {
        this.upvotes = upvotes;
    }

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
