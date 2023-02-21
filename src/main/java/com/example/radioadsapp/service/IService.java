package com.example.radioadsapp.service;
import com.example.radioadsapp.dto.UserDto;
import com.example.radioadsapp.model.User;

import java.util.List;

public interface IService<T,ID> {
    T save(T t);
    void delete(ID id);

}