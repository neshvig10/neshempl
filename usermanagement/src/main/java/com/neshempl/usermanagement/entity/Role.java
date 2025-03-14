package com.neshempl.usermanagement.entity;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

@Entity
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Long roleId;


    @Column(nullable = false,name = "role_name")
    public String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role(){}

    public Role(String roleName) {
        this.roleName = roleName;
    }
}