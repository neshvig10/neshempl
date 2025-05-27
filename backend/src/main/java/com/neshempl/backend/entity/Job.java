package com.neshempl.backend.entity;

import com.neshempl.backend.service.JobService;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_company")
    private String companyName;

    @Column(name = "job_experience")
    private Long experienceRequired;

    @Column(name = "job_salary")
    private Integer salary;

    @Column(name = "job_description",length=10485760)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobSkill> jobSkills = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobLocation> jobLocations = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private JobPostUser jobPostUser;


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<JobSkill> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(List<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }

    public List<JobLocation> getJobLocations() {
        return jobLocations;
    }

    public void setJobLocations(List<JobLocation> jobLocations) {
        this.jobLocations = jobLocations;
    }

    public JobPostUser getJobPostUser() {
        return jobPostUser;
    }

    public void setJobPostUsers(JobPostUser jobPostUser) {
        this.jobPostUser = jobPostUser;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(Long experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Job(){

    }



}
