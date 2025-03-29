package com.neshempl.backend.service;

import com.neshempl.backend.dto.JobRequest;
import org.springframework.stereotype.Service;

@Service
public interface JobService {

    String postJob(JobRequest jobRequest);
}
