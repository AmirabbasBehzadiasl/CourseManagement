package com.amir.CourseManagement.Repository;

import com.amir.CourseManagement.Model.Professor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Optional<Professor> findProfessorByFirstName(String firstName);

    Optional<Professor> findProfessorByPersonnelNumber(int id);
}
