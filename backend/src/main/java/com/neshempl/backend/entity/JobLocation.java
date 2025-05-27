package com.neshempl.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class JobLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_location_id")
    private Long jobLocationId;


    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonIgnore
    public Job job;

    @ManyToOne
    @JoinColumn(name = "location_id")
    public Location location;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
