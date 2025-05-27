package com.neshempl.backend.repository;

import com.neshempl.backend.entity.Job;
import com.neshempl.backend.entity.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill,Long> {
    void deleteByJob(Job job);


    boolean existsByJob(Job job);
}
