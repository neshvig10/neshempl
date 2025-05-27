package com.neshempl.backend.controller;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.Job;
import com.neshempl.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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


}
