package com.cabbooking.models;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cab_id")
    private Cab cab;

    private String pickup;
    private String dropoff;
    private String status;

    public Booking() {}

    public Booking(User user, Cab cab, String pickup, String dropoff) {
        this.user = user;
        this.cab = cab;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.status = "CONFIRMED";
    }

    // getters & setters
    public int getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Cab getCab() { return cab; }
    public void setCab(Cab cab) { this.cab = cab; }
    public String getPickup() { return pickup; }
    public void setPickup(String pickup) { this.pickup = pickup; }
    public String getDropoff() { return dropoff; }
    public void setDropoff(String dropoff) { this.dropoff = dropoff; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
