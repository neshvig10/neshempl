package com.neshempl.backend.service.impl;


import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.repository.ResumeRepository;
import com.neshempl.backend.service.ResumeService;
import org.springframework.core.io.Resource;
import org.bouncycastle.util.StoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ResumeServiceImplement implements ResumeService {


    @Value(("${FILE_DESTINATION}"))
    private String fileDestination;

    @Autowired
    ResumeRepository resumeRepository;

    @Override
    public List<Resume> getResumeForUser(Long userId) {
        return resumeRepository.getReferenceByResumeUserId(userId);
    }

    @Override
    public String postResume(MultipartFile resumeFile,Long userId) {
        String fileName = resumeFile.getOriginalFilename();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());


        try{
//            if (resumeFile.isEmpty()) {
//                throw new StoreException("Failed to store empty file.",new Throwable());
//            }
            File dir = new File(fileDestination);
            if (!dir.exists()) {
                dir.mkdirs(); // Create directory if it doesn't exist
            }
            Path path = Paths.get(fileDestination, resumeFile.getOriginalFilename()+timeStamp);
            resumeFile.transferTo(path);

        }catch(Exception e){
            System.out.println(e);
            return "Error occurred while uploading";
        }
        Resume resume = new Resume();
        resume.setResumeFilePath(fileDestination+resumeFile.getOriginalFilename()+timeStamp);
        resume.setResumeUserId(userId);
        resume.setResumeName(resumeFile.getOriginalFilename());
        resumeRepository.save(resume);
        return "Resume uploaded successfully";
    }

    @Override
    public ResponseEntity<Resource> getResume(Long resumeId) throws MalformedURLException, FileNotFoundException {
        Resume resume = resumeRepository.getReferenceById(resumeId);
        Path path = Paths.get(resume.getResumeFilePath());
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }
}
