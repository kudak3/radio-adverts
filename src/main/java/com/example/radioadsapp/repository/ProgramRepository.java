package com.example.radioadsapp.repository;

import com.example.radioadsapp.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program,Long> {
}
