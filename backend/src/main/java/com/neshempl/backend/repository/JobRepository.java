package com.neshempl.backend.repository;

import com.neshempl.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {


    @Query(
            value = "SELECT job_id FROM job_post_user WHERE user_id = :userId",
            nativeQuery = true
    )
    List<Long> getReferenceByUserPosted(@Param("userId") Long userId);
}
