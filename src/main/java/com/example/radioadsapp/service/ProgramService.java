package com.example.radioadsapp.service;

import com.example.radioadsapp.model.Program;

import java.util.List;

public interface ProgramService extends IService<Program, Long> {
    List<Program> getAll();
}
