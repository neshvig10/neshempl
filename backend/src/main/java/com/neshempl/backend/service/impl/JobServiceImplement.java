package com.neshempl.backend.service.impl;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.*;
import com.neshempl.backend.repository.*;
import com.neshempl.backend.service.JobService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class JobServiceImplement implements JobService {


    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobSkillRepository jobSkillRepository;

    @Autowired
    JobLocationRepository jobLocationRepository;

    @Autowired
    JobPostUserRepository jobPostUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public String postJob(JobRequest jobRequest) {

        Job job = new Job();

        job.setJobTitle(jobRequest.getJobTitle());

        job.setCompanyName(jobRequest.getCompanyName());



        job.setExperienceRequired(jobRequest.getExperienceRequired());

        job.setSalary(jobRequest.getSalary());

        job.setDescription(jobRequest.getDescription());


        jobRepository.save(job);

        String[] skills = jobRequest.getSkills().split(",");
        Set<Skill> jobSkills = new HashSet<>();
        for (String skill : skills){
            Skill skill1 = skillRepository.getReferenceBySkillName(skill);
            if (skill1 == null){
                skill1 = new Skill(skill);
                skillRepository.save(skill1);
            }
            jobSkills.add(skill1);
        }

        for (Skill skills1 : jobSkills){
            JobSkill jobSkill = new JobSkill();
            jobSkill.setJob(job);
            jobSkill.setSkill(skills1);
            job.getJobSkills().add(jobSkill);
        }

        String[] locations = jobRequest.getLocations().split(",");
        Set<Location> jobLocations = new HashSet<>();
        for (String location : locations){
            Location location1 = locationRepository.getReferenceByLocationName(location);
            if (location1 == null){
                location1 = new Location(location);
                locationRepository.save(location1);
            }
            jobLocations.add(location1);
        }
        for (Location location1 : jobLocations){
            JobLocation jobLocation = new JobLocation();
            jobLocation.setJob(job);
            jobLocation.setLocation(location1);
            job.getJobLocations().add(jobLocation);
        }
        JobPostUser jobPostUser = new JobPostUser();
        jobPostUser.setJob(job);
        jobPostUser.setUser(userRepository.getByUserId(jobRequest.getPostedUserId()));

        job.setJobPostUsers(jobPostUser);

        jobRepository.save(job);

        return "Job posted successfully";
    }

    @Override
    public List<Job> listJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long jobId){
        return jobRepository.findById(jobId).get();
    }

    public void deleteJobPosting(Long jobId){
        Job job = this.getJobById(jobId);
        if (jobLocationRepository.existsByJob(job)) {
            jobLocationRepository.deleteByJob(job);
        }
        if (jobSkillRepository.existsByJob(job)){
            jobSkillRepository.deleteByJob(job);
        }
        if (jobPostUserRepository.existsByJob(job)){
            JobPostUserRepository.deleteByJob(job);
        }
        jobRepository.deleteById(jobId);
    }


    @Override
    public List<Long> listJobsByUserId(Long userId) {
        return jobRepository.getReferenceByUserPosted(userId);
    }
}
