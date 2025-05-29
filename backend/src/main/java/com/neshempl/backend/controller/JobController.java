package com.neshempl.backend.controller;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.JobApplication;
import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.entity.User;
import com.neshempl.backend.service.JobService;
import jakarta.persistence.Entity;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/job")
public class JobController {


    @Autowired
    JobService jobService;

    @GetMapping(value = "/getjobs")
    public List<Job> listJobs(){
        return jobService.listJobs();
    }

    @GetMapping(value = "/jobpostedbyuser")
    public List<Long> listJobsByUser(@RequestParam Long userId) {
        return jobService.listJobsByUserId(userId);
    }

    @GetMapping(value = "/jobbyid")
    public Job jobByJobId(@RequestParam Long jobId){
        return jobService.getJobById(jobId);
    }

    @PostMapping(value = "/postjob")
    public String postJob(JobRequest jobRequest){
        return jobService.postJob(jobRequest);
    }

    @DeleteMapping(value = "/deletejob")
    public void deleteJobPosting(Long jobId){
        jobService.deleteJobPosting(jobId);
    }

    @PostMapping(value = "/applytojob")
    public String applyToJob(@RequestParam Long jobId, @RequestParam Long userId, @RequestParam Long resumeId){
        return jobService.applyToJob(jobId,userId,resumeId);
    }

    @GetMapping(value = "/appliedtojobornot")
    public boolean appliedToJobOrNot(@RequestParam Long jobId,@RequestParam Long userId){
        return jobService.appliedToJobOrNot(jobId,userId);
    }

    @GetMapping(value = "/applicantsofjob")
    public List<Object[]> applicantsOfJob(@RequestParam Long jobId){
        return jobService.applicationsOfJob(jobId);
    }

}
