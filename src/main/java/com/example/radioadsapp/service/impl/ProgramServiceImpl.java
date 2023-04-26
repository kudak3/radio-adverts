package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Program;
import com.example.radioadsapp.repository.ProgramRepository;
import com.example.radioadsapp.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Program save(Program show) {
        return programRepository.save(show);
    }

    @Override
    public void delete(Long aLong) {
        this.programRepository.deleteById(aLong);

    }

    public List<Program> getAll(){
        return programRepository.findAll();
    }

    public Program get(Long id) {
        return programRepository.findById(id).orElse(null);

    }
}
