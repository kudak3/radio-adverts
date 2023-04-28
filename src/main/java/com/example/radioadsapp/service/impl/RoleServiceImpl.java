package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.repository.RoleRepository;
import com.example.radioadsapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }



    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id){
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
