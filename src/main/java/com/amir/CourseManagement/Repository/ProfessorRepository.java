package com.amir.CourseManagement.Repository;

import com.amir.CourseManagement.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
