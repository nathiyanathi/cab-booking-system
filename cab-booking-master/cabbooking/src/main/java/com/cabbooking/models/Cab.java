package com.cabbooking.models;

import javax.persistence.*;

@Entity
@Table(name = "cabs")
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number_plate;
    private String driver_name;
    private String model;
    private boolean available = true;

    public Cab() {}

    public Cab(String number_plate, String driver_name, String model) {
        this.number_plate = number_plate;
        this.driver_name = driver_name;
        this.model = model;
    }

    // getters & setters
    public int getId() { return id; }
    public String getNumber_plate() { return number_plate; }
    public void setNumber_plate(String number_plate) { this.number_plate = number_plate; }
    public String getDriver_name() { return driver_name; }
    public void setDriver_name(String driver_name) { this.driver_name = driver_name; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
