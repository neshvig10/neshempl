package com.neshempl.backend.service;
import com.neshempl.backend.entity.Resume;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;


@Service
public interface ResumeService {

    List<Resume> getResumeForUser(Long userId);

    String postResume(MultipartFile resumeFile,Long userId);

    Resource getResume(Long resumeId) throws MalformedURLException, FileNotFoundException;
}
