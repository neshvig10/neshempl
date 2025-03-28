package com.neshempl.backend.controller;

import com.neshempl.backend.dto.JobRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {


    @PostMapping(value = "/postjob")
    public String postJob(JobRequest jobRequest){
        return "";
    }

}
