package com.neshempl.backend.entity;


import jakarta.persistence.*;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long jobApplicationId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id",referencedColumnName = "job_id")
    public Job job;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    public User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resume_id",referencedColumnName = "resume_id")
    public Resume resume;


    public Long getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplicationId(Long jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }

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

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public JobApplication(){

    }

    public JobApplication(Job job, User user, Resume resume) {
        this.job = job;
        this.user = user;
        this.resume = resume;
    }
}
