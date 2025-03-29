package com.amir.CourseManagement.Repository;

import com.amir.CourseManagement.Model.Faculty;
import com.amir.CourseManagement.Model.Student;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    Optional<Faculty> findFacultiesByName(String facultyName);
}
