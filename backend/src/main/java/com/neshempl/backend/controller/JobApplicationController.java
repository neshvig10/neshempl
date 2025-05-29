package com.neshempl.backend.controller;


import com.neshempl.backend.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/jobapplication")
public class JobApplicationController {

    @Autowired
    ResumeService resumeService;

    @GetMapping(value = "/analyseJobApplication")
    public String analyzeJobApplication(@RequestParam Long resumeId,@RequestParam Long jobId) throws IOException {
        return resumeService.analyzeJobApplication(resumeId,jobId);
    }
}
