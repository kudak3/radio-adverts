package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.Role;
import com.example.radioadsapp.model.User;
import com.example.radioadsapp.repository.RoleRepository;
import com.example.radioadsapp.repository.UserRepository;
import com.example.radioadsapp.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        System.out.println("================");
        System.out.println(userDto);
        Role role = roleRepository.findById(userDto.getRole()).orElse(null);
        System.out.println(role);
        if(role == null){
            role = checkRoleExist();
        }
        //encrypt the password once we integrate spring security
        User user = new User(userDto.getFirstName(),
                userDto.getLastName(), userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()), Arrays.asList(role),true,userDto.getPhoto());
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User>  getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

//    public boolean hasAuthority(String au){
//        boolean isAuth = false;
//        for (GrantedAuthority authority : auth.getAuthorities()) {
//                isAuth = au.equals(authority.getAuthority());
//              break;
//        }
//        return isAuth;
//    }



    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ADMIN");
        return roleRepository.save(role);
    }


}