package com.neshempl.backend.service;
import com.neshempl.backend.entity.Resume;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@Service
public interface ResumeService {

    List<Resume> getResumeForUser(Long userId);

    String postResume(MultipartFile resumeFile,Long userId);
}
