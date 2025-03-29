package com.neshempl.backend.controller;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
@CrossOrigin("*")
public class JobController {


    @Autowired
    JobService jobService;


    @PostMapping(value = "/postjob")
    public String postJob(JobRequest jobRequest){
        return jobService.postJob(jobRequest);
    }

}
