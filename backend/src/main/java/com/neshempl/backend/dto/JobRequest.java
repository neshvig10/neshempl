package com.neshempl.backend.dto;

import java.util.Set;

public class JobRequest {

    private String jobTitle;

    private Set<String> skills;

    private Set<String> locations;

    private String companyName;

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

    private Long experienceRequired;

    private Integer salary;

    private String description;


}
