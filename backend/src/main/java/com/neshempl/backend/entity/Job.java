package com.neshempl.backend.entity;

import jakarta.persistence.*;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "job_locations",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations;



    @Column(name = "job_experience")
    private Long experienceRequired;

    @Column(name = "job_salary")
    private Integer salary;

    @Column(name = "job_description")
    private String description;


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

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
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

    public Job(String jobTitle, String companyName, Set<Skill> skills, Set<Location> locations,  Long experienceRequired, Integer salary, String description) {
        this.jobTitle = jobTitle;
        this.skills = skills;
        this.locations = locations;
        this.companyName = companyName;
        this.experienceRequired = experienceRequired;
        this.salary = salary;
        this.description = description;
    }
}
