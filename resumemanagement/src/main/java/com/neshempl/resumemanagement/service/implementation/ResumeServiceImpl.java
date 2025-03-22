package com.neshempl.resumemanagement.service.implementation;


import com.neshempl.resumemanagement.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ResumeServiceImpl implements ResumeService {
    public ResponseEntity<String> uploadResume(MultipartFile resumeFile) {
        String fileName = resumeFile.getOriginalFilename();
        try{
            resumeFile.transferTo(new File("~/Desktop/NeshEmpl/resumeFiles"+fileName));
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error occurred while uploading");
        }
        return ResponseEntity.ok("File uploaded successfully");
    }


    public MultipartFile retrieveResume(Long resumeFileId){
        return "";
    }
}

