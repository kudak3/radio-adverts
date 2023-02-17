package com.example.radioadsapp.repository;


import com.example.radioadsapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);

}
