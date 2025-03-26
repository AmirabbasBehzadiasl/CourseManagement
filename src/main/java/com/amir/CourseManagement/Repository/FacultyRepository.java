package com.amir.CourseManagement.Repository;

import com.amir.CourseManagement.Model.Faculty;
import com.amir.CourseManagement.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

}
