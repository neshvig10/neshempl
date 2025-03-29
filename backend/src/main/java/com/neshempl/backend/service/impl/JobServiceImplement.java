package com.neshempl.backend.service.impl;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.Location;
import com.neshempl.backend.entity.Skill;
import com.neshempl.backend.repository.JobRepository;
import com.neshempl.backend.repository.LocationRepository;
import com.neshempl.backend.repository.SkillRepository;
import com.neshempl.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class JobServiceImplement implements JobService {


    @Autowired
    JobRepository jobRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public String postJob(JobRequest jobRequest) {
        Job job = new Job();

        job.setJobTitle(jobRequest.getJobTitle());

        job.setCompanyName(jobRequest.getCompanyName());

        String[] skills = jobRequest.getSkills().split(",");
        Set<Skill> jobSkills = new HashSet<>();
        for (String skill : skills){
            Skill skill1 = skillRepository.getReferenceBySkillName(skill);
            if (skill1 == null){
                skill1 = new Skill(skill);
            }
            jobSkills.add(skill1);
        }
        job.setSkills(jobSkills);

        String[] locations = jobRequest.getLocations().split(",");
        Set<Location> jobLocations = new HashSet<>();
        for (String location : locations){
            Location location1 = locationRepository.getReferenceByLocationName(location);
            if (location1 == null){
                location1 = new Location(location);
            }
            jobLocations.add(location1);
        }
        job.setLocations(jobLocations);

        job.setExperienceRequired(jobRequest.getExperienceRequired());

        job.setSalary(jobRequest.getSalary());

        job.setDescription(jobRequest.getDescription());

        jobRepository.save(job);

        return "Job posted successfully";
    }

    @Override
    public List<Job> listJobs() {
        return jobRepository.findAll();
    }
}
