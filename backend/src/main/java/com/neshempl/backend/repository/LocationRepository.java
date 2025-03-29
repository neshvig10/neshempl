package com.neshempl.backend.repository;

import com.neshempl.backend.entity.Location;
import com.neshempl.backend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Location getReferenceByLocationName(String location);
}
