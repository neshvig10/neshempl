package com.neshempl.usermanagement.repository;


import com.neshempl.usermanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {



    Role getRoleByRoleName(String userRole);

    boolean existsByRoleName(String userRole);
}
