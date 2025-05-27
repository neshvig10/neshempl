package com.neshempl.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
public class JobPostUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_user_id")
    private Long jobPostId;


    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonIgnore
    public Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
