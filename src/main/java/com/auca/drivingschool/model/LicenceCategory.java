package com.auca.drivingschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "licence_category")
public class LicenceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "licenceCategories")
    @JsonIgnore
    private List<Instructor> instructors;

    public LicenceCategory() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Instructor> getInstructors() { return instructors; }
    public void setInstructors(List<Instructor> instructors) { this.instructors = instructors; }
}