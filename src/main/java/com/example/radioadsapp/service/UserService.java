package com.example.radioadsapp.service;
import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    void deleteUser(Long id);

    User findByEmail(String email);

    List<User> getAll();
}