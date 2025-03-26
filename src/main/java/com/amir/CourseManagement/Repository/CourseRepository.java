package com.amir.CourseManagement.Repository;

import com.amir.CourseManagement.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findCourseByName(String courseName);
}
