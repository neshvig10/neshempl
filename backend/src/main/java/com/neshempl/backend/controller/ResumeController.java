package com.neshempl.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @PostMapping(value = "/uploadresume")
    public String uploadResume(MultipartFile resumeFile){
        
    }

    
}
