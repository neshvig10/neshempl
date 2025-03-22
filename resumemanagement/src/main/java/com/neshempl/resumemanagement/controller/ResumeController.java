package com.neshempl.resumemanagement.controller;


import com.neshempl.resumemanagement.service.ResumeService;
import com.neshempl.resumemanagement.service.implementation.ResumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile resumeFile){
        return resumeService.uploadResume(resumeFile);
    }

    public MultipartFile retrieveResume(Long resumeId){
        return resumeService.retrieveResume(resumeId);
    }


}
