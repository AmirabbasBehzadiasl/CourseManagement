package com.amir.CourseManagement.Repository;

import com.amir.CourseManagement.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findStudentByStudentCode(String studentCode);
}
