package com.neshempl.backend.service.impl;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.*;
import com.neshempl.backend.repository.*;
import com.neshempl.backend.service.JobService;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.Pair;
import org.checkerframework.checker.units.qual.A;
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

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Autowired
    ResumeRepository resumeRepository;

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
            Skill skill1 = skillRepository.getReferenceBySkillName(skill.trim());
            if (skill1 == null){
                skill1 = new Skill(skill.trim());
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
            Location location1 = locationRepository.getReferenceByLocationName(location.trim());
            if (location1 == null){
                location1 = new Location(location.trim());
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


    public String applyToJob(Long jobId, Long userId, Long resumeId){
        try{
            JobApplication jobApplication = new JobApplication(jobRepository.getReferenceById(jobId),userRepository.getReferenceById(userId), resumeRepository.getReferenceById(resumeId));
            jobApplicationRepository.save(jobApplication);
            return "Applied to job successfully";
        }catch (Exception error) {
            return error.toString();
        }
    }

    public boolean appliedToJobOrNot(Long jobId, Long userId){
        List<User> listOfUsersApplied = jobApplicationRepository.findUserByJob(jobRepository.getReferenceById(jobId));
        System.out.println("listofusersapplied"+listOfUsersApplied);
        return listOfUsersApplied.contains(userRepository.getReferenceById(userId));
    }

    public List<Object[]> applicationsOfJob(Long jobId){
        return jobApplicationRepository.findAllByJob(jobRepository.getReferenceById(jobId));
    }

}
