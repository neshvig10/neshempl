package com.neshempl.backend.repository;


import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.JobApplication;
import com.neshempl.backend.entity.Resume;
import com.neshempl.backend.entity.User;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {

        @Query(
                value = "SELECT ja.user FROM JobApplication ja WHERE ja.job = :job"
        )
        List<User> findUserByJob(@Param("job") Job job);

        @Query(
                value = "SELECT ja.user,ja.resume FROM JobApplication ja WHERE ja.job = :job"
        )
        List<Object[]> findAllByJob(Job job);
}
