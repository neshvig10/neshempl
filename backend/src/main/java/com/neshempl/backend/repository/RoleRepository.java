package com.neshempl.backend.repository;


import com.neshempl.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {



    Role getRoleByRoleName(String userRole);

    boolean existsByRoleName(String userRole);
}
