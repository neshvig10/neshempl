package com.neshempl.resumemanagement.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ResumeService {


    public ResponseEntity<String> uploadResume(MultipartFile resumeFile) throws IOException;

    MultipartFile retrieveResume(Long resumeId);
}
