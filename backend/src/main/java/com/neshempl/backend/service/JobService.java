package com.neshempl.backend.service;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface JobService {

    String postJob(JobRequest jobRequest);

    List<Job> listJobs();

    List<Long> listJobsByUserId(Long userId);

    Job getJobById(Long jobId);

    void deleteJobPosting(Long jobId);
}
