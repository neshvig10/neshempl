package com.neshempl.backend.repository;

import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.JobLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLocationRepository extends JpaRepository<JobLocation,Long> {

    void deleteByJob(Job job);


    boolean existsByJob(Job job);
}
