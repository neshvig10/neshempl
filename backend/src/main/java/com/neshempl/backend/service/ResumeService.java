package com.neshempl.backend.service;
import com.neshempl.backend.entity.Resume;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;


@Service
public interface ResumeService {

    List<Resume> getResumeForUser(Long userId);

    String postResume(MultipartFile resumeFile,Long userId);

    ResponseEntity<Resource> getResume(Long resumeId) throws IOException, InterruptedException;

    String callRemotiveApi(String category, String title) throws IOException, InterruptedException;

    String callRapidLinkedinAPI(String keywords) throws IOException, InterruptedException;

    String analyzeResume(Long resumeId) throws IOException, InterruptedException, ParseException;
}
