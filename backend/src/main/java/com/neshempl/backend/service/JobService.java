package com.neshempl.backend.service;

import com.neshempl.backend.dto.JobRequest;
import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.JobApplication;
import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.entity.User;
import org.antlr.v4.runtime.misc.Pair;
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

    String applyToJob(Long jobId, Long userId, Long resumeId);

    boolean appliedToJobOrNot(Long jobId, Long userId);


    List<Object[]> applicationsOfJob(Long jobId);
}
