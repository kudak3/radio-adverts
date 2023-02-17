package com.example.radioadsapp.service;

import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public void saveRole(Role role){
        roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }

    public void updateRole(Role role){
        roleRepository.save(role);
    }

    public Role getRole(Long id){
        Role role = roleRepository.findById(id).orElse(null);

        if (role==null) {

            throw new RuntimeException("Cannot find Role with id: " + id);

        }

        else return role;
    }

}
