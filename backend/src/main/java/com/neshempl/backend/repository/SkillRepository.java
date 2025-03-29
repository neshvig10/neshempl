package com.neshempl.backend.repository;

import com.neshempl.backend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    Skill getReferenceBySkillName(String skill);
}
