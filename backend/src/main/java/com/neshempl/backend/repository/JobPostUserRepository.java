package com.neshempl.backend.repository;


import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.JobPostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostUserRepository extends JpaRepository<JobPostUser,Long> {

    static void deleteByJob(Job job) {
    }


    boolean existsByJob(Job job);
}
