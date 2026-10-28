package com.auca.drivingschool.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity 
@Table (name ="Vehicle")

public class Vehicle {
    @Id 
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID id;

    private int fleetNumber;
    private String name;

    public Vehicle() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public int getFleetNumber() { return fleetNumber; }
    public void setFleetNumber(int fleetNumber) { this.fleetNumber = fleetNumber; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
