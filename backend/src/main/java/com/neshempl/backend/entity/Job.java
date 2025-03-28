package com.neshempl.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Set;


@Table(name = "job")
@Entity
public class Job {

    private Long jobId;
    private String jobTitle;

    private Set<String> skills;

    private Set<String> locations;

    private String companyName;

    private Long experienceRequired;

    private Integer salary;

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

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public Set<String> getLocations() {
        return locations;
    }

    public void setLocations(Set<String> locations) {
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

    public Job(Long jobId, String jobTitle, Set<String> skills, Set<String> locations, String companyName, Long experienceRequired, Integer salary, String description) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.skills = skills;
        this.locations = locations;
        this.companyName = companyName;
        this.experienceRequired = experienceRequired;
        this.salary = salary;
        this.description = description;
    }
}
