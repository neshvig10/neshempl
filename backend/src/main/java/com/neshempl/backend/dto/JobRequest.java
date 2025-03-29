package com.neshempl.backend.dto;

import java.util.Set;

public class JobRequest {

    private String jobTitle;

    private String skills;

    private String locations;

    private String companyName;

    private Long experienceRequired;

    private Integer salary;

    private String description;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
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



}
