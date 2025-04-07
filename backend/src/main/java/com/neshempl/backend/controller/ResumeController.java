package com.neshempl.backend.controller;

import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.repository.ResumeRepository;
import com.neshempl.backend.service.ResumeService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/resume")
public class ResumeController {


    @Autowired
    ResumeService resumeService;

    @GetMapping(value = "/getresumesforuser")
    public List<Resume> getResumeForUser(Long userId){
        return resumeService.getResumeForUser(userId);
    }

    @GetMapping(value = "/getresume")
    public ResponseEntity<Resource> getResume(Long resumeId) throws IOException {
        return resumeService.getResume(resumeId);
    }


    @PostMapping(value = "/uploadresume")
    public String uploadResume(@RequestParam("file") MultipartFile resumeFile,Long userId){
        System.out.println("file"+resumeFile.getOriginalFilename()+"userId"+userId);
        return resumeService.postResume(resumeFile,userId);
    }


}

