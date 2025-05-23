package com.neshempl.backend.controller;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.Job;
import com.neshempl.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/job")
@CrossOrigin("*")
public class JobController {


    @Autowired
    JobService jobService;

    @GetMapping(value = "/getjobs")
    public List<Job> listJobs(){
        return jobService.listJobs();
    }

    @PostMapping(value = "/postjob")
    public String postJob(JobRequest jobRequest){
        return jobService.postJob(jobRequest);
    }


}
